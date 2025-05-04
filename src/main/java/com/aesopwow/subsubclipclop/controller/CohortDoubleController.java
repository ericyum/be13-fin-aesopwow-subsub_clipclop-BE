package com.aesopwow.subsubclipclop.controller;


import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisInsightRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisInsightResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisRemainHeatmapRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisRemainHeatmapResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisUserDataRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisUserDataResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisVisualizationRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisVisualizationResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.service.cohortdouble.CohortDoubleAnalysisInsightService;
import com.aesopwow.subsubclipclop.domain.analysis.service.cohortdouble.CohortDoubleAnalysisRemainHeatmapService;
import com.aesopwow.subsubclipclop.domain.analysis.service.cohortdouble.CohortDoubleAnalysisUserDataService;
import com.aesopwow.subsubclipclop.domain.analysis.service.cohortdouble.CohortDoubleAnalysisVisualizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/cohorts")
@RequiredArgsConstructor
public class CohortDoubleController {
    private final CohortDoubleAnalysisRemainHeatmapService cohortDoubleAnalysisRemainHeatmapService;
    private final CohortDoubleAnalysisVisualizationService cohortDoubleAnalysisVisualizationService;
    private final CohortDoubleAnalysisInsightService cohortDoubleAnalysisInsightService;

    private final CohortDoubleAnalysisUserDataService userDataService;


    @PostMapping("/double/remain-heatmap")
    public ResponseEntity<CohortDoubleAnalysisRemainHeatmapResponseDto> fetchDoubleAnalysis(
            @RequestBody CohortDoubleAnalysisRemainHeatmapRequestDto requestDto
    ) {
        CohortDoubleAnalysisRemainHeatmapResponseDto result =
                cohortDoubleAnalysisRemainHeatmapService.fetchDoubleAnalysisChart(requestDto.getClusterType());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/double/visualization")
    public ResponseEntity<CohortDoubleAnalysisVisualizationResponseDto> fetchDoubleVisualization(
            @RequestBody CohortDoubleAnalysisVisualizationRequestDto requestDto
    ) {
        return ResponseEntity.ok(cohortDoubleAnalysisVisualizationService.fetchVisualization(requestDto));
    }

    @PostMapping("/double/insight")
    public ResponseEntity<CohortDoubleAnalysisInsightResponseDto> fetchDoubleInsight(
            @RequestBody CohortDoubleAnalysisInsightRequestDto requestDto
    ) {
        return ResponseEntity.ok(cohortDoubleAnalysisInsightService.fetchInsight(requestDto));
    }

    @PostMapping("/double/user-data")
    public ResponseEntity<CohortDoubleAnalysisUserDataResponseDto> fetchUserData(
            @RequestBody CohortDoubleAnalysisUserDataRequestDto requestDto
    ) {
        return ResponseEntity.ok(userDataService.fetchUserData(requestDto));
    }
}
