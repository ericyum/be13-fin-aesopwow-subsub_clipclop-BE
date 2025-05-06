package com.aesopwow.subsubclipclop.domain.analysis.service.cohortdouble;


import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisInsightRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisInsightResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisRemainHeatmapResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisUserDataRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisUserDataResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisVisualizationRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisVisualizationResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.repository.cohortdouble.CohortDoubleAnalysisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CohortDoubleAnalysisServiceImpl implements CohortDoubleAnalysisService {

    private final CohortDoubleAnalysisRepository repository;

    @Override
    public CohortDoubleAnalysisInsightResponseDto fetchInsight(CohortDoubleAnalysisInsightRequestDto requestDto) {
        return repository.fetchInsight(requestDto.getClusterType());
    }

    @Override
    public CohortDoubleAnalysisRemainHeatmapResponseDto fetchRemainHeatmap(String clusterType) {
        return repository.fetchRemainHeatmap(clusterType);
    }

    @Override
    public CohortDoubleAnalysisVisualizationResponseDto fetchVisualization(CohortDoubleAnalysisVisualizationRequestDto requestDto) {
        return repository.fetchVisualization(requestDto.getClusterType());
    }

    @Override
    public CohortDoubleAnalysisUserDataResponseDto fetchUserData(CohortDoubleAnalysisUserDataRequestDto requestDto) {
        return repository.fetchUserData(requestDto);
    }
}