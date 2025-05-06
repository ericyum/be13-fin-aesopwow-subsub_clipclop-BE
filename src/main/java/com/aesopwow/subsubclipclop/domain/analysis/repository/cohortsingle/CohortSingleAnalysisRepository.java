package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortsingle;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisInsightResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisRemainHeatmapResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisUserDataRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisUserDataResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisVisualizationResponseDto;

public interface CohortSingleAnalysisRepository {
    CohortSingleAnalysisInsightResponseDto fetchInsight(String clusterType);
    CohortSingleAnalysisRemainHeatmapResponseDto fetchRemainHeatmap(String clusterType);
    CohortSingleAnalysisVisualizationResponseDto fetchVisualization(String clusterType);
    CohortSingleAnalysisUserDataResponseDto fetchUserData(CohortSingleAnalysisUserDataRequestDto requestDto);
}