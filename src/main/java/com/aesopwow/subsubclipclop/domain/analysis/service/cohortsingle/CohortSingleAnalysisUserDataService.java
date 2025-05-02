package com.aesopwow.subsubclipclop.domain.analysis.service.cohortsingle;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisUserDataRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisUserDataResponseDto;

public interface CohortSingleAnalysisUserDataService {
    CohortSingleAnalysisUserDataResponseDto fetchUserData(CohortSingleAnalysisUserDataRequestDto requestDto);
}