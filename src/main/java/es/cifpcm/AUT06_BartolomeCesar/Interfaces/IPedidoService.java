package es.cifpcm.AUT06_BartolomeCesar.Interfaces;

import es.cifpcm.AUT06_BartolomeCesar.Models.Pedido;

import java.util.List;

public interface IPedidoService {

    public List<Pedido> getPedidoList();

    public Pedido getPedido(Integer id);

    public void addPedido(Pedido pedido);
}
