package es.cifpcm.AUT06_BartolomeCesar.Interfaces;

import es.cifpcm.AUT06_BartolomeCesar.Models.Producto;

import java.util.List;

public interface IProductoService {

    public List<Producto> getProductoList();

    public Producto getProducto(Integer id);

    public void addProducto(Producto producto);

    public void editProducto(Integer id,Producto producto);

    public void deleteProducto(Integer id);
}
