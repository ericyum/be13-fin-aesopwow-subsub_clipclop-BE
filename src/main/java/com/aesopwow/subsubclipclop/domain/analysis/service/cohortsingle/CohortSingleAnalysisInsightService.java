package com.aesopwow.subsubclipclop.domain.analysis.service.cohortsingle;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisInsightRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisInsightResponseDto;

public interface CohortSingleAnalysisInsightService {
    CohortSingleAnalysisInsightResponseDto fetchInsight(CohortSingleAnalysisInsightRequestDto requestDto);
}