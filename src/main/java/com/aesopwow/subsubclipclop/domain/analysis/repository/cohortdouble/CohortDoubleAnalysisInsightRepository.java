package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortdouble;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisInsightResponseDto;

public interface CohortDoubleAnalysisInsightRepository {
    CohortDoubleAnalysisInsightResponseDto fetchInsight(String clusterType);
}