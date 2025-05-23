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
import com.aesopwow.subsubclipclop.domain.api.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/analysis")
@RequiredArgsConstructor
public class AnalysisController {

    private final AnalysisService analysisService;

    @GetMapping("/cohort")
    public ResponseEntity<Void> getAnalysis() {
        return ResponseEntity.ok().build();
    }

    private final ApiService apiService;

    @GetMapping("")
    public ResponseEntity<byte[]> getAnalysisResult(
            @RequestParam String infoDbNo,
            @RequestParam String originTable) {

        // 파라미터 유효성 검사
        if (infoDbNo == null || infoDbNo.isBlank() || originTable == null || originTable.isBlank()) {
            throw new IllegalArgumentException("필수 파라미터가 누락되었습니다.");
        }

        byte[] fileBytes = apiService.getAnalysisResult(infoDbNo, originTable);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition
                .attachment()
                .filename("dashboard_" + infoDbNo + ".csv") // ✅ 파일 이름 명시
                .build());

        return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
    }

    @PostMapping("/cohort/{type}/remain-heatmap")
    public ResponseEntity<?> fetchRemainHeatmap(@PathVariable String type, @RequestBody Map<String, Object> body) {
        if ("single".equalsIgnoreCase(type)) {
            String clusterType = (String) body.get("clusterType");
            CohortSingleAnalysisRemainHeatmapRequestDto requestDto =
                    new CohortSingleAnalysisRemainHeatmapRequestDto(clusterType);
            return ResponseEntity.ok(analysisService.fetchSingleRemainHeatmap(requestDto));
        } else if ("double".equalsIgnoreCase(type)) {
            String firstClusterType = (String) body.get("firstClusterType");
            String secondClusterType = (String) body.get("secondClusterType");
            CohortDoubleAnalysisRemainHeatmapRequestDto requestDto =
                    new CohortDoubleAnalysisRemainHeatmapRequestDto(firstClusterType, secondClusterType);
            return ResponseEntity.ok(analysisService.fetchDoubleRemainHeatmap(requestDto));
        } else {
            return ResponseEntity.badRequest().body("Invalid type: must be 'single' or 'double'");
        }
    }

    @PostMapping("/cohort/{type}/visualization")
    public ResponseEntity<?> fetchVisualization(@PathVariable String type, @RequestBody Map<String, Object> body) {
        if ("single".equalsIgnoreCase(type)) {
            String clusterType = (String) body.get("clusterType");
            return ResponseEntity.ok(analysisService.fetchSingleVisualization(
                    new CohortSingleAnalysisVisualizationRequestDto(clusterType)));
        } else if ("double".equalsIgnoreCase(type)) {
            String firstClusterType = (String) body.get("firstClusterType");
            String secondClusterType = (String) body.get("secondClusterType");
            return ResponseEntity.ok(analysisService.fetchDoubleVisualization(
                    new CohortDoubleAnalysisVisualizationRequestDto(firstClusterType, secondClusterType)));
        } else {
            return ResponseEntity.badRequest().body("Invalid type: must be 'single' or 'double'");
        }
    }

    @PostMapping("/cohort/{type}/insight")
    public ResponseEntity<?> fetchInsight(@PathVariable String type, @RequestBody Map<String, Object> body) {
        if ("single".equalsIgnoreCase(type)) {
            String clusterType = (String) body.get("clusterType");
            return ResponseEntity.ok(analysisService.fetchSingleInsight(
                    new CohortSingleAnalysisInsightRequestDto(clusterType)));
        } else if ("double".equalsIgnoreCase(type)) {
            String firstClusterType = (String) body.get("firstClusterType");
            String secondClusterType = (String) body.get("secondClusterType");
            return ResponseEntity.ok(analysisService.fetchDoubleInsight(
                    new CohortDoubleAnalysisInsightRequestDto(firstClusterType, secondClusterType)));
        } else {
            return ResponseEntity.badRequest().body("Invalid type: must be 'single' or 'double'");
        }
    }

    @PostMapping("/cohort/{type}/user-data")
    public ResponseEntity<?> fetchUserData(@PathVariable String type, @RequestBody Map<String, Object> body) {
        try {
            if ("single".equalsIgnoreCase(type)) {
                String clusterType = (String) body.get("clusterType");
                List<String> fields = (List<String>) body.get("fields");

                CohortSingleAnalysisUserDataRequestDto dto =
                        new CohortSingleAnalysisUserDataRequestDto(clusterType, fields);

                return ResponseEntity.ok(analysisService.fetchSingleUserData(dto));

            } else if ("double".equalsIgnoreCase(type)) {
                String firstClusterType = (String) body.get("firstClusterType");
                String secondClusterType = (String) body.get("secondClusterType");

                // ✅ 수정된 부분: "fields"로 key 통일
                List<String> fields = (List<String>) body.get("fields");

                CohortDoubleAnalysisUserDataRequestDto dto =
                        new CohortDoubleAnalysisUserDataRequestDto(firstClusterType, secondClusterType, fields);

                return ResponseEntity.ok(analysisService.fetchDoubleUserData(dto));

            } else {
                return ResponseEntity.badRequest().body("Invalid type: must be 'single' or 'double'");
            }

        } catch (ClassCastException e) {
            return ResponseEntity.badRequest().body("Invalid request body format: " + e.getMessage());
        }
    }

}