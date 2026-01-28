package com.jayzebra.rtm.domain.port.in;

import com.jayzebra.rtm.domain.dto.RtmOperationRequestDto;

// RtmOperationUseCase.java
public interface RtmOperationUseCase {
    //function to perform rtmOperation
    void performRtmOperation(RtmOperationRequestDto requestDto);
}
