package gm.zona_fit.repositorio;

import gm.zona_fit.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
}
// solo con esto ya tengo habilitados los metodos de guardar, actualizar, eliminar, etc.
//cuando le paso el Cliente, como que perzonalizo la interface y los metodos en base a mi clase./