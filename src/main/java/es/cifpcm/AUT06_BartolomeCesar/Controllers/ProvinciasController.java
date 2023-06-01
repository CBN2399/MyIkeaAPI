package es.cifpcm.AUT06_BartolomeCesar.Controllers;

import es.cifpcm.AUT06_BartolomeCesar.Interfaces.IProvinciaService;
import es.cifpcm.AUT06_BartolomeCesar.Models.Provincia;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProvinciasController {

    @Autowired
    IProvinciaService provSer;

    @GetMapping("/provincias")
    public ResponseEntity<List<Provincia>> getlist(){

        List<Provincia> provinciaList = provSer.getProvinciaList();
        return new ResponseEntity<>(provinciaList, HttpStatus.OK);
    }
}
