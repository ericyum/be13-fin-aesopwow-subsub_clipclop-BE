package com.aesopwow.subsubclipclop.controller;

import com.aesopwow.subsubclipclop.domain.analysis.dto.behaviorpattern.*;
import com.aesopwow.subsubclipclop.domain.analysis.dto.insight.*;
import com.aesopwow.subsubclipclop.domain.analysis.dto.remainheatmap.*;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.*;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.*;

import com.aesopwow.subsubclipclop.domain.analysis.service.behaviorpattern.*;
import com.aesopwow.subsubclipclop.domain.analysis.service.insight.*;
import com.aesopwow.subsubclipclop.domain.analysis.service.remainheatmap.*;

import com.aesopwow.subsubclipclop.domain.analysis.service.cohortsingle.*;
import com.aesopwow.subsubclipclop.domain.analysis.service.cohortdouble.*;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cohorts")
@RequiredArgsConstructor
public class AnalysisController {

    private final CohortAnalysisBehaviorPatternService cohortAnalysisBehaviorPatternService;
    private final CohortAnalysisInsightService cohortAnalysisInsightService;
    private final CohortAnalysisRemainHeatmapService cohortAnalysisRemainHeatmapService;

    private final CohortSingleAnalysisService cohortSingleAnalysisService;

    private final CohortDoubleAnalysisService cohortDoubleAnalysisService;


    // ---------------- 기본 분석(삭제 예정) ----------------

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

// ---------------- 단일 코호트 ----------------

    @PostMapping("/single/remain-heatmap")
    public ResponseEntity<CohortSingleAnalysisRemainHeatmapResponseDto> fetchSingleRemainHeatmap(
            @RequestBody CohortSingleAnalysisRemainHeatmapRequestDto requestDto
    ) {
        return ResponseEntity.ok(
                cohortSingleAnalysisService.fetchRemainHeatmap(requestDto.getClusterType())
        );
    }

    @PostMapping("/single/visualization")
    public ResponseEntity<CohortSingleAnalysisVisualizationResponseDto> fetchSingleVisualization(
            @RequestBody CohortSingleAnalysisVisualizationRequestDto requestDto
    ) {
        return ResponseEntity.ok(cohortSingleAnalysisService.fetchVisualization(requestDto));
    }

    @PostMapping("/single/insight")
    public ResponseEntity<CohortSingleAnalysisInsightResponseDto> fetchSingleInsight(
            @RequestBody CohortSingleAnalysisInsightRequestDto requestDto
    ) {
        return ResponseEntity.ok(cohortSingleAnalysisService.fetchInsight(requestDto));
    }

    @PostMapping("/single/user-data")
    public ResponseEntity<CohortSingleAnalysisUserDataResponseDto> fetchSingleUserData(
            @RequestBody CohortSingleAnalysisUserDataRequestDto requestDto
    ) {
        return ResponseEntity.ok(cohortSingleAnalysisService.fetchUserData(requestDto));
    }


// ---------------- 이중 코호트 ----------------

    @PostMapping("/double/remain-heatmap")
    public ResponseEntity<CohortDoubleAnalysisRemainHeatmapResponseDto> fetchDoubleRemainHeatmap(
            @RequestBody CohortDoubleAnalysisRemainHeatmapRequestDto requestDto
    ) {
        return ResponseEntity.ok(
                cohortDoubleAnalysisService.fetchRemainHeatmap(requestDto.getClusterType())
        );
    }

    @PostMapping("/double/visualization")
    public ResponseEntity<CohortDoubleAnalysisVisualizationResponseDto> fetchDoubleVisualization(
            @RequestBody CohortDoubleAnalysisVisualizationRequestDto requestDto
    ) {
        return ResponseEntity.ok(cohortDoubleAnalysisService.fetchVisualization(requestDto));
    }

    @PostMapping("/double/insight")
    public ResponseEntity<CohortDoubleAnalysisInsightResponseDto> fetchDoubleInsight(
            @RequestBody CohortDoubleAnalysisInsightRequestDto requestDto
    ) {
        return ResponseEntity.ok(cohortDoubleAnalysisService.fetchInsight(requestDto));
    }

    @PostMapping("/double/user-data")
    public ResponseEntity<CohortDoubleAnalysisUserDataResponseDto> fetchDoubleUserData(
            @RequestBody CohortDoubleAnalysisUserDataRequestDto requestDto
    ) {
        return ResponseEntity.ok(cohortDoubleAnalysisService.fetchUserData(requestDto));
    }

}