package com.aesopwow.subsubclipclop.domain.analysis.service.cohortdouble;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisInsightRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisInsightResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.repository.cohortdouble.CohortDoubleAnalysisInsightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CohortDoubleAnalysisInsightServiceImpl implements CohortDoubleAnalysisInsightService {

    private final CohortDoubleAnalysisInsightRepository repository;

    @Override
    public CohortDoubleAnalysisInsightResponseDto fetchInsight(CohortDoubleAnalysisInsightRequestDto requestDto) {
        return repository.fetchInsight(requestDto.getClusterType());
    }
}