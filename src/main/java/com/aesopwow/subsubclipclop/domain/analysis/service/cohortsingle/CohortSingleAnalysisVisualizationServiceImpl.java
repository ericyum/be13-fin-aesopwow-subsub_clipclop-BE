package com.aesopwow.subsubclipclop.domain.analysis.service.cohortsingle;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisVisualizationRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisVisualizationResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.repository.cohortsingle.CohortSingleAnalysisVisualizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CohortSingleAnalysisVisualizationServiceImpl implements CohortSingleAnalysisVisualizationService {

    private final CohortSingleAnalysisVisualizationRepository repository;

    @Override
    public CohortSingleAnalysisVisualizationResponseDto fetchVisualization(CohortSingleAnalysisVisualizationRequestDto requestDto) {
        return repository.fetchVisualization(requestDto.getClusterType());
    }
}