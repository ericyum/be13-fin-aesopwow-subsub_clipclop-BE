package com.aesopwow.subsubclipclop.controller;

import com.aesopwow.subsubclipclop.domain.analysis.dto.behaviorpattern.CohortAnalysisBehaviorPatternRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.behaviorpattern.CohortAnalysisBehaviorPatternResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisInsightRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisInsightResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisRemainHeatmapResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisRemainHeatmapRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisUserDataRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisUserDataResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisVisualizationRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisVisualizationResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.insight.CohortAnalysisInsightRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.insight.CohortAnalysisInsightResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.remainheatmap.CohortAnalysisRemainHeatmapRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.remainheatmap.CohortAnalysisRemainHeatmapResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.service.behaviorpattern.CohortAnalysisBehaviorPatternService;
import com.aesopwow.subsubclipclop.domain.analysis.service.cohortsingle.CohortSingleAnalysisInsightService;
import com.aesopwow.subsubclipclop.domain.analysis.service.cohortsingle.CohortSingleAnalysisRemainHeatmapService;
import com.aesopwow.subsubclipclop.domain.analysis.service.cohortsingle.CohortSingleAnalysisUserDataService;
import com.aesopwow.subsubclipclop.domain.analysis.service.cohortsingle.CohortSingleAnalysisVisualizationService;
import com.aesopwow.subsubclipclop.domain.analysis.service.insight.CohortAnalysisInsightService;
import com.aesopwow.subsubclipclop.domain.analysis.service.remainheatmap.CohortAnalysisRemainHeatmapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cohorts")
@RequiredArgsConstructor
public class AnalysisController {

    private final CohortAnalysisBehaviorPatternService cohortAnalysisBehaviorPatternService;
    private final CohortAnalysisInsightService cohortAnalysisInsightService;
    private final CohortAnalysisRemainHeatmapService cohortAnalysisRemainHeatmapService;

    private final CohortSingleAnalysisRemainHeatmapService cohortSingleAnalysisRemainHeatmapService;
    private final CohortSingleAnalysisVisualizationService cohortSingleAnalysisVisualizationService;
    private final CohortSingleAnalysisInsightService cohortSingleAnalysisInsightService;

    private final CohortSingleAnalysisUserDataService userDataService;

    @PostMapping("/behavior-pattern")
    public CohortAnalysisBehaviorPatternResponseDto fetchBehaviorPattern(
            @RequestBody CohortAnalysisBehaviorPatternRequestDto requestDto
    ) {
        return cohortAnalysisBehaviorPatternService.fetchBehaviorPattern(requestDto);
    }

    @PostMapping("/insight")
    public CohortAnalysisInsightResponseDto fetchInsight(
            @RequestBody CohortAnalysisInsightRequestDto requestDto
    ) {
        return cohortAnalysisInsightService.fetchInsight(requestDto);
    }

    @PostMapping("/remain-heatmap")
    public CohortAnalysisRemainHeatmapResponseDto fetchRemainHeatmap(
            @RequestBody CohortAnalysisRemainHeatmapRequestDto requestDto
    ) {
        return cohortAnalysisRemainHeatmapService.fetchRemainHeatmap(requestDto);
    }

    @PostMapping("/single/remain-heatmap")
    public ResponseEntity<CohortSingleAnalysisRemainHeatmapResponseDto> fetchSingleAnalysis(
            @RequestBody CohortSingleAnalysisRemainHeatmapRequestDto requestDto
    ) {
        CohortSingleAnalysisRemainHeatmapResponseDto result =
                cohortSingleAnalysisRemainHeatmapService.fetchSingleAnalysisChart(requestDto.getClusterType());
        return ResponseEntity.ok(result);
    }

    @PostMapping("/single/visualization")
    public ResponseEntity<CohortSingleAnalysisVisualizationResponseDto> fetchSingleVisualization(
            @RequestBody CohortSingleAnalysisVisualizationRequestDto requestDto
    ) {
        return ResponseEntity.ok(cohortSingleAnalysisVisualizationService.fetchVisualization(requestDto));
    }

    @PostMapping("/single/insight")
    public ResponseEntity<CohortSingleAnalysisInsightResponseDto> fetchSingleInsight(
            @RequestBody CohortSingleAnalysisInsightRequestDto requestDto
    ) {
        return ResponseEntity.ok(cohortSingleAnalysisInsightService.fetchInsight(requestDto));
    }

    @PostMapping("/single/user-data")
    public ResponseEntity<CohortSingleAnalysisUserDataResponseDto> fetchUserData(
            @RequestBody CohortSingleAnalysisUserDataRequestDto requestDto
    ) {
        return ResponseEntity.ok(userDataService.fetchUserData(requestDto));
    }
}