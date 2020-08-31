package co.mil.fac.cetad.sptintegrator.controllers;

import co.mil.fac.cetad.sptintegrator.models.SptModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/rest/spt")
@Slf4j
public class SPTController {

    private static List<String> names = Arrays.asList("SPT0001","SPT0002","SPT0003","SPT0004");
    private static List<SptModel> sptModels = names.stream()
            .map(spt -> {
                SptModel sptModel = new SptModel();
                sptModel.setId(spt.substring(6));
                sptModel.setName(spt);
                sptModel.setLocation("4.53454, -74.43563456");
                sptModel.setCreated(LocalDateTime.now());
                return sptModel;
            }).collect(Collectors.toList());

    @GetMapping("/devices/{idDevice}")
    public ResponseEntity<SptModel> getNameServer(@PathVariable String idDevice) {
        SptModel name = sptModels.stream()
                .filter(spt -> spt.getId().equals(idDevice))
                .findAny().orElse(null);
        if(name == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(name, HttpStatus.OK);
    }

    @GetMapping("/devices")
    public ResponseEntity<List<SptModel>> getAllDevices() {
        return new ResponseEntity<>(sptModels, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SptModel> createSpt(@RequestBody SptModel sptModel) {
        sptModels.add(sptModel);
        return new ResponseEntity<>(sptModel, HttpStatus.CREATED);
    }

    @DeleteMapping("/devices/{idDevice}")
    public ResponseEntity<Void> createSpt(@PathVariable String idDevice) {
        SptModel sptModel = sptModels.stream().filter(spt -> spt.getId().equals(idDevice)).findAny().get();
        if(sptModel == null)
            return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
        sptModels.remove(sptModel);
        log.info("Spt {} was deleted", idDevice);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
