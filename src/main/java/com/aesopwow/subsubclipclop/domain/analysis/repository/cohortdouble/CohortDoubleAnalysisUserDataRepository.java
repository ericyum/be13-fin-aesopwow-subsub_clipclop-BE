package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortdouble;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisUserDataRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisUserDataResponseDto;

public interface CohortDoubleAnalysisUserDataRepository {
    CohortDoubleAnalysisUserDataResponseDto fetchUserData(CohortDoubleAnalysisUserDataRequestDto requestDto);
}