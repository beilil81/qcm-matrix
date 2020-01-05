package com.humanup.matrix.qcm.controllers;

import com.humanup.matrix.qcm.bs.ChoiceBS;
import com.humanup.matrix.qcm.vo.ChoiceVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class ChoiceController {

    @Autowired
    private ChoiceBS choiceBS;

    @Operation(summary = "Create Choice",description = "Create choice answer by ",tags = { "choice" })
    @RequestMapping(value = "/choice", method= RequestMethod.POST,consumes = {"application/json"})
    @ResponseBody
    public ResponseEntity createChoice(@RequestBody ChoiceVO choice) {
        Optional<Object> findChoice = Optional.ofNullable(choiceBS.findChoiceByQuestionId(choice.getQuestionId()));

        if(findChoice.isPresent() && null!=findChoice.get()){
            return ResponseEntity.status(HttpStatus.FOUND).body("this choice is founded");
        }

        choiceBS.createChoice(choice);
        return ResponseEntity.status(HttpStatus.CREATED).body(choice);
    }

    @Operation(summary = "Find all choice", description = "Find all choice", tags = { "choice" })
    @RequestMapping(value="/choice/all", method=RequestMethod.GET)
    @ResponseBody
    public ResponseEntity getAllChoiceInfo(){
        List<ChoiceVO> findChoice= choiceBS.findListChoice();

        if(findChoice.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(findChoice);
    }
}
