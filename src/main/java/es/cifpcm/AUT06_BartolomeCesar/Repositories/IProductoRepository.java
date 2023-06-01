package es.cifpcm.AUT06_BartolomeCesar.Repositories;

import es.cifpcm.AUT06_BartolomeCesar.Models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository<Producto,Integer> {
}
