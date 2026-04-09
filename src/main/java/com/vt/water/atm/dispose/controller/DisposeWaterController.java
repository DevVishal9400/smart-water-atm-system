package com.vt.water.atm.dispose.controller;

import com.vt.water.atm.dispose.dto.DisposeWaterRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/dispose")
public class DisposeWaterController {

    //dispose water & reduce balance
    @PostMapping
    public ResponseEntity disposeWater(@RequestBody @Valid DisposeWaterRequestDto disposeWaterRequestDto){

        //check card exists or not
        //check available amount is> disposal amount
        //sucess

        return  null;
    }
}
