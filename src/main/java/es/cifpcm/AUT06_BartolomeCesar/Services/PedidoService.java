package es.cifpcm.AUT06_BartolomeCesar.Services;

import es.cifpcm.AUT06_BartolomeCesar.Interfaces.IPedidoService;
import es.cifpcm.AUT06_BartolomeCesar.Models.Pedido;
import es.cifpcm.AUT06_BartolomeCesar.Repositories.IPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService implements IPedidoService {

    @Autowired
    IPedidoRepository pedRe;

    @Override
    public List<Pedido> getPedidoList() {
        return pedRe.findAll();
    }

    @Override
    public Pedido getPedido(Integer id) {
        return pedRe.findById(id).orElse(null);
    }

    @Override
    public void addPedido(Pedido pedido) {

        pedRe.save(pedido);
    }
}
