package com.aesopwow.subsubclipclop.domain.analysis.service.cohortdouble;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisInsightRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisInsightResponseDto;

public interface CohortDoubleAnalysisInsightService {
    CohortDoubleAnalysisInsightResponseDto fetchInsight(CohortDoubleAnalysisInsightRequestDto requestDto);
}