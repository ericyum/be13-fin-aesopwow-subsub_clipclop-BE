package com.aesopwow.subsubclipclop.domain.analysis.service.cohortsingle;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.*;
import com.aesopwow.subsubclipclop.domain.analysis.repository.cohortsingle.CohortSingleAnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CohortSingleAnalysisServiceImpl implements CohortSingleAnalysisService {

    private final CohortSingleAnalysisRepository repository;

    @Override
    public CohortSingleAnalysisInsightResponseDto fetchInsight(CohortSingleAnalysisInsightRequestDto requestDto) {
        return repository.fetchInsight(requestDto.getClusterType());
    }

    @Override
    public CohortSingleAnalysisRemainHeatmapResponseDto fetchRemainHeatmap(String clusterType) {
        return repository.fetchRemainHeatmap(clusterType);
    }

    @Override
    public CohortSingleAnalysisVisualizationResponseDto fetchVisualization(CohortSingleAnalysisVisualizationRequestDto requestDto) {
        return repository.fetchVisualization(requestDto.getClusterType());
    }

    @Override
    public CohortSingleAnalysisUserDataResponseDto fetchUserData(CohortSingleAnalysisUserDataRequestDto requestDto) {
        return repository.fetchUserData(requestDto);
    }
}