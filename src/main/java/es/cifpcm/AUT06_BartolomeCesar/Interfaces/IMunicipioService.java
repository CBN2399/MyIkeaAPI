package es.cifpcm.AUT06_BartolomeCesar.Interfaces;

import es.cifpcm.AUT06_BartolomeCesar.Models.Municipio;

import java.util.List;

public interface IMunicipioService {

    public List<Municipio> getMunicipioList();

    public Municipio getMunicipio(Integer id);
}
