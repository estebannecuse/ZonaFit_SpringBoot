package gm.zona_fit;

import gm.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
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
		logger.info("Aplicacion Zona Fit ***");
	}
}
