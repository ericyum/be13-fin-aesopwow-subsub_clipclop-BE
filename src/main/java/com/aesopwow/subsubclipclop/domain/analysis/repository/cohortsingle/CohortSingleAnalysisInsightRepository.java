package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortsingle;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisInsightResponseDto;

public interface CohortSingleAnalysisInsightRepository {
    CohortSingleAnalysisInsightResponseDto fetchInsight(String clusterType);
}