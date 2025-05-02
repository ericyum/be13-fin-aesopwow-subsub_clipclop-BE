package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortsingle;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisUserDataRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisUserDataResponseDto;

public interface CohortSingleAnalysisUserDataRepository {
    CohortSingleAnalysisUserDataResponseDto fetchUserData(CohortSingleAnalysisUserDataRequestDto requestDto);
}