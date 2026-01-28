package com.jayzebra.rtm.adapter.in.rest;

import com.jayzebra.rtm.domain.dto.RtmOperationRequestDto;
import com.jayzebra.rtm.domain.port.in.RtmOperationUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

//function to create rtm
@RestController
@RequestMapping("/rtm")
@Tag(name="RealTimeManagement")
public class RtmController {

    private final RtmOperationUseCase rtmOperationUseCase;

    public RtmController(RtmOperationUseCase rtmOperationUseCase) {
        this.rtmOperationUseCase = rtmOperationUseCase;
    }

    @PostMapping("/operations")
    @ResponseStatus(HttpStatus.ACCEPTED) // Returns a 202 Accepted status code
    public void performRtmOperation(@RequestBody @Valid RtmOperationRequestDto requestDto) {
        rtmOperationUseCase.performRtmOperation(requestDto);
    }
}

