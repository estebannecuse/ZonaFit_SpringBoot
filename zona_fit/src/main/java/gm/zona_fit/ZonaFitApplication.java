package gm.zona_fit;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

//@SpringBootApplication // esto es para que spring ejecute esta clase como main
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class); // esto es para poder mandar info a la consola y que clase esta mandando la info , es para mayor detalle

	public static void main(String[] args) {
		logger.info("Iniciando la aplicacion amigoo");
		SpringApplication.run(ZonaFitApplication.class, args); // se ejecuta la " fabrica de objetos spring empieza a crear todos los objetos para nosotros poder usar la app
		logger.info("Aplicacion finalizada amigoo");
	}

	// cuando se levanta la app y tenemos los objetos disponibles, ahora si podemos ejecutar la app y usar los objetos
	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}

	private void zonaFitApp(){
		boolean salir = false;
		var consola = new Scanner(System.in);
		System.out.println("********************* Bienvenido a la aplicacion *********************");
		while(!salir){
			try{
				var opcion = mostrarMenu(consola);
				salir = ejecutarOpciones(consola, opcion);
			}catch(Exception e){
				System.out.println("Ocurrio un error" + e.getMessage());
			}
			System.out.println();
		}
	}

	private int mostrarMenu(Scanner consola){
		System.out.println("""
				Menu:
				1. Listar Clientes
				2. Buscar Cliente
				3. Agregar Cliente
				4. Modificar Cliente
				5. Eliminar Cliente
				6. Salir
				Elige una opcion... \s""");
		return Integer.parseInt(consola.nextLine());
	}

	private boolean ejecutarOpciones(Scanner consola, int opcion){
		boolean salir = false;
		switch (opcion){
			case 1-> {
				List<Cliente> clientes = clienteServicio.listarClientes();
				System.out.println("**Listado de Clientes **");
				clientes.forEach(System.out::println);
			}
			case 2-> {
				System.out.println("Ingrese el numero de Id del cliente");
				Cliente cliente = clienteServicio.buscarClientePorId(Integer.parseInt(consola.nextLine()));
				System.out.println("El cliente con id proporcionado es: " + cliente);
			}
			case 3->{
				System.out.println("** Agregar cliente **");
				System.out.println("Ingrese el nombre del Usuario: ");
				String nombre = consola.nextLine();
				System.out.println("Ingrese el apellido del Usuario: ");
				String apellido = consola.nextLine();
				System.out.println("Ingrese la membresia del Usuario: ");
				Integer membresia = Integer.parseInt(consola.nextLine());
				Cliente cliente = new Cliente();
				cliente.setNombre(nombre);
				cliente.setApellido(apellido);
				cliente.setMembresia(membresia);
				clienteServicio.guardarCliente(cliente);
				System.out.println(" cliente agregado con exito : +" + cliente);
			}
			case 4 ->{
				System.out.println("*** Modificar Cliente ***");
				System.out.println("Ingrese el id del cliente a modificar");
				Integer idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
				if(cliente != null){
					System.out.println("Ingrese el nombre del Usuario: ");
					String nombre = consola.nextLine();
					System.out.println("Ingrese el apellido del Usuario: ");
					String apellido = consola.nextLine();
					System.out.println("Ingrese la membresia del Usuario: ");
					Integer membresia = Integer.parseInt(consola.nextLine());
					cliente.setNombre(nombre);
					cliente.setApellido(apellido);
					cliente.setMembresia(membresia);
					clienteServicio.guardarCliente(cliente);
					System.out.println("Cliente actualizado con exito");
				}else{
					System.out.println("Cliente no encontrado");
				}
			}
			case 5->{
				System.out.println("*** Eliminar Cliente ***");
				System.out.println("Ingrese el id del cliente a eliminar");
				Integer idCliente = Integer.parseInt(consola.nextLine());
				Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
				if(cliente != null){
					clienteServicio.eliminarCliente(cliente);
					System.out.println("Cliente eliminado con exito");
				}else{
					System.out.println("Cliente inexistente");
				}
			}
			case 6->{
				System.out.println("Hasta pronto");
				salir =true;
			}
			default -> System.out.println("Opcion invalida");
		}

		return salir;
	}

}
