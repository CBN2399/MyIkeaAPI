package es.cifpcm.AUT06_BartolomeCesar.Services;

import es.cifpcm.AUT06_BartolomeCesar.Interfaces.IProvinciaService;
import es.cifpcm.AUT06_BartolomeCesar.Models.Provincia;
import es.cifpcm.AUT06_BartolomeCesar.Repositories.IProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaService implements IProvinciaService {

    @Autowired
    IProvinciaRepository provRe;

    @Override
    public List<Provincia> getProvinciaList() {
        return provRe.findAll();
    }

    @Override
    public Provincia getProvincia(Integer id) {
        return provRe.findById(id).orElse(null);
    }
}
