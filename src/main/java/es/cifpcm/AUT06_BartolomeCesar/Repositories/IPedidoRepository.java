package es.cifpcm.AUT06_BartolomeCesar.Repositories;

import es.cifpcm.AUT06_BartolomeCesar.Models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPedidoRepository extends JpaRepository<Pedido,Integer> {
}
