package es.cifpcm.AUT06_BartolomeCesar.Services;

import es.cifpcm.AUT06_BartolomeCesar.Interfaces.IPedidoService;
import es.cifpcm.AUT06_BartolomeCesar.Interfaces.IProductoService;
import es.cifpcm.AUT06_BartolomeCesar.Models.Producto;
import es.cifpcm.AUT06_BartolomeCesar.Repositories.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService implements IProductoService {


    @Autowired
    IProductoRepository prodRe;


    @Override
    public List<Producto> getProductoList() {
        return prodRe.findAll();
    }

    @Override
    public Producto getProducto(Integer id) {
        return prodRe.findById(id).orElse(null);
    }

    @Override
    public void addProducto(Producto producto) {
        prodRe.save(producto);
    }

    @Override
    public void editProducto(Integer id, Producto producto) {

        Producto prod = prodRe.findById(id).orElse(null);
        if(prod != null){
            prodRe.save(producto);
        }
    }

    @Override
    public void deleteProducto(Integer id) {
        prodRe.deleteById(id);
    }
}
