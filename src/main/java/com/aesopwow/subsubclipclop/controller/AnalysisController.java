package com.aesopwow.subsubclipclop.controller;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisInsightRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisRemainHeatmapRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisUserDataRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisVisualizationRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisInsightRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisRemainHeatmapRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisUserDataRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.CohortSingleAnalysisVisualizationRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService analysisService;

    @PostMapping("/cohort/{type}/remain-heatmap")
    public ResponseEntity<?> fetchRemainHeatmap(
            @PathVariable String type,
            @RequestBody Map<String, Object> body
    ) {
        String clusterType = (String) body.get("clusterType");

        if ("single".equalsIgnoreCase(type)) {
            CohortSingleAnalysisRemainHeatmapRequestDto requestDto =
                    new CohortSingleAnalysisRemainHeatmapRequestDto(clusterType);
            return ResponseEntity.ok(analysisService.fetchSingleRemainHeatmap(requestDto));
        } else if ("double".equalsIgnoreCase(type)) {
            CohortDoubleAnalysisRemainHeatmapRequestDto requestDto =
                    new CohortDoubleAnalysisRemainHeatmapRequestDto(clusterType);
            return ResponseEntity.ok(analysisService.fetchDoubleRemainHeatmap(requestDto));
        } else {
            return ResponseEntity.badRequest().body("Invalid type: must be 'single' or 'double'");
        }
    }


    @PostMapping("/cohort/{type}/visualization")
    public ResponseEntity<?> fetchVisualization(@PathVariable String type, @RequestBody Map<String, Object> body) {
        String clusterType = (String) body.get("clusterType");

        if ("single".equalsIgnoreCase(type)) {
            return ResponseEntity.ok(analysisService.fetchSingleVisualization(
                    new CohortSingleAnalysisVisualizationRequestDto(clusterType)));
        } else if ("double".equalsIgnoreCase(type)) {
            return ResponseEntity.ok(analysisService.fetchDoubleVisualization(
                    new CohortDoubleAnalysisVisualizationRequestDto(clusterType)));
        } else {
            return ResponseEntity.badRequest().body("Invalid type: must be 'single' or 'double'");
        }
    }

    @PostMapping("/cohort/{type}/insight")
    public ResponseEntity<?> fetchInsight(@PathVariable String type, @RequestBody Map<String, Object> body) {
        String clusterType = (String) body.get("clusterType");

        if ("single".equalsIgnoreCase(type)) {
            return ResponseEntity.ok(analysisService.fetchSingleInsight(
                    new CohortSingleAnalysisInsightRequestDto(clusterType)));
        } else if ("double".equalsIgnoreCase(type)) {
            return ResponseEntity.ok(analysisService.fetchDoubleInsight(
                    new CohortDoubleAnalysisInsightRequestDto(clusterType)));
        } else {
            return ResponseEntity.badRequest().body("Invalid type: must be 'single' or 'double'");
        }
    }

    @PostMapping("/cohort/{type}/user-data")
    public ResponseEntity<?> fetchUserData(@PathVariable String type, @RequestBody Map<String, Object> body) {
        try {
            if ("single".equalsIgnoreCase(type)) {
                CohortSingleAnalysisUserDataRequestDto dto = new CohortSingleAnalysisUserDataRequestDto(
                        (List<String>) body.get("fields")
                );
                return ResponseEntity.ok(analysisService.fetchSingleUserData(dto));
            } else if ("double".equalsIgnoreCase(type)) {
                CohortDoubleAnalysisUserDataRequestDto dto = new CohortDoubleAnalysisUserDataRequestDto(
                        (List<String>) body.get("fields")
                );
                return ResponseEntity.ok(analysisService.fetchDoubleUserData(dto));
            } else {
                return ResponseEntity.badRequest().body("Invalid type: must be 'single' or 'double'");
            }
        } catch (ClassCastException e) {
            return ResponseEntity.badRequest().body("Invalid request body format: " + e.getMessage());
        }
    }

}