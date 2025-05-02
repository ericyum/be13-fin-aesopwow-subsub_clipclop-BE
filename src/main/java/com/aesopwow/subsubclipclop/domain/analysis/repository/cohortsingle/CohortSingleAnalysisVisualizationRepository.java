package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortsingle;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisVisualizationResponseDto;

public interface CohortSingleAnalysisVisualizationRepository {
    CohortSingleAnalysisVisualizationResponseDto fetchVisualization(String clusterType);
}