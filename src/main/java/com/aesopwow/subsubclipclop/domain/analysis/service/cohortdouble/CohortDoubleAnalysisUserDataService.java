package com.aesopwow.subsubclipclop.domain.analysis.service.cohortdouble;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisUserDataRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisUserDataResponseDto;

public interface CohortDoubleAnalysisUserDataService {
    CohortDoubleAnalysisUserDataResponseDto fetchUserData(CohortDoubleAnalysisUserDataRequestDto requestDto);
}