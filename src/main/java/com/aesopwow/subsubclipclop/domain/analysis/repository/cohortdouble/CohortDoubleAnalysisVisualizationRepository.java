package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortdouble;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisVisualizationResponseDto;

public interface CohortDoubleAnalysisVisualizationRepository {
    CohortDoubleAnalysisVisualizationResponseDto fetchVisualization(String clusterType);
}