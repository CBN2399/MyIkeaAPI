package es.cifpcm.AUT06_BartolomeCesar.Services;

import es.cifpcm.AUT06_BartolomeCesar.Interfaces.IMunicipioService;
import es.cifpcm.AUT06_BartolomeCesar.Models.Municipio;
import es.cifpcm.AUT06_BartolomeCesar.Repositories.IMunicipioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MunicipioService  implements IMunicipioService {

    @Autowired
    IMunicipioRepository munRe;

    @Override
    public List<Municipio> getMunicipioList() {
        return munRe.findAll();
    }
    @Override
    public Municipio getMunicipio(Integer id) {
        return munRe.findById(id).orElse(null);
    }
}
