package es.cifpcm.AUT06_BartolomeCesar.Interfaces;

import es.cifpcm.AUT06_BartolomeCesar.Models.Provincia;

import java.util.List;

public interface IProvinciaService {

    public List<Provincia> getProvinciaList();

    public Provincia getProvincia(Integer id);
}
