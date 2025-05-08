package com.aesopwow.subsubclipclop.domain.analysis.repository;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.*;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortsingle.*;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

@Repository
public class AnalysisRepositoryImpl implements AnalysisRepository {

    // ========== SINGLE ==========

    @Override
    public CohortSingleAnalysisInsightResponseDto fetchSingleInsight(String clusterType) {
        try {
            InputStream csvStream = getClass().getClassLoader().getResourceAsStream("mock/single-insight.csv");
            if (csvStream == null) throw new IllegalArgumentException("CSV 파일 없음");

            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream));
            reader.readLine();
            String[] parts = reader.readLine().split(",");

            return new CohortSingleAnalysisInsightResponseDto(parts[0].trim());
        } catch (Exception e) {
            throw new RuntimeException("단일 인사이트 분석 실패", e);
        }
    }

    @Override
    public CohortSingleAnalysisRemainHeatmapResponseDto fetchSingleRemainHeatmap(CohortSingleAnalysisRemainHeatmapRequestDto requestDto) {
        try {
            InputStream csvStream = getClass().getClassLoader().getResourceAsStream("mock/single-remain-heatmap.csv");
            if (csvStream == null) throw new IllegalArgumentException("CSV 파일 없음");

            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream));
            String line;
            String content = "";
            List<String> columnLabels = new ArrayList<>();
            List<List<String>> dataRows = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                switch (line.trim()) {
                    case "content":
                        content = reader.readLine().trim();
                        break;
                    case "columns":
                        columnLabels = Arrays.asList(reader.readLine().split(","));
                        break;
                    case "rows":
                        while ((line = reader.readLine()) != null) {
                            dataRows.add(Arrays.asList(line.split(",")));
                        }
                        break;
                }
            }

            return new CohortSingleAnalysisRemainHeatmapResponseDto(content, columnLabels, dataRows);
        } catch (Exception e) {
            throw new RuntimeException("단일 히트맵 분석 실패", e);
        }
    }

    @Override
    public CohortSingleAnalysisVisualizationResponseDto fetchSingleVisualization(String clusterType) {
        try {
            InputStream csvStream = getClass().getClassLoader().getResourceAsStream("mock/single-visualization.csv");
            if (csvStream == null) throw new IllegalArgumentException("CSV 파일 없음");

            BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream));
            reader.readLine();
            String[] parts = reader.readLine().split(",");

            String imageAPath = parts[0].trim();
            String imageBPath = parts[1].trim();

            String base64A = encodeBase64(imageAPath);
            String base64B = encodeBase64(imageBPath);

            return new CohortSingleAnalysisVisualizationResponseDto(base64A, base64B);
        } catch (Exception e) {
            throw new RuntimeException("단일 시각화 분석 실패", e);
        }
    }

    @Override
    public CohortSingleAnalysisUserDataResponseDto fetchSingleUserData(CohortSingleAnalysisUserDataRequestDto requestDto) {
        try {
            InputStream stream = getClass().getClassLoader().getResourceAsStream("mock/single-user-data.csv");
            if (stream == null) throw new IllegalArgumentException("CSV 파일 없음");

            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            String[] headers = reader.readLine().split(",");
            List<Map<String, String>> table = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                Map<String, String> row = new LinkedHashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    if (requestDto.getFields().contains(headers[i].trim())) {
                        row.put(headers[i].trim(), values[i].trim());
                    }
                }
                table.add(row);
            }

            return new CohortSingleAnalysisUserDataResponseDto(table);
        } catch (Exception e) {
            throw new RuntimeException("단일 유저 데이터 로딩 실패", e);
        }
    }

    // ========== DOUBLE ==========

    @Override
    public CohortDoubleAnalysisInsightResponseDto fetchDoubleInsight(String firstClusterType, String secondClusterType) {
        try (BufferedReader reader = getReader("mock/double-insight.csv")) {
            reader.readLine();
            String[] parts = reader.readLine().split(",");
            return new CohortDoubleAnalysisInsightResponseDto(parts[0].trim(), parts[1].trim());
        } catch (Exception e) {
            throw new RuntimeException("이중 인사이트 분석 실패", e);
        }
    }

    @Override
    public CohortDoubleAnalysisRemainHeatmapResponseDto fetchDoubleRemainHeatmap(CohortDoubleAnalysisRemainHeatmapRequestDto requestDto) {
        try (BufferedReader reader = getReader("mock/double-remain-heatmap.csv")) {
            String line;
            String firstContent = "";
            List<String> firstColumnLabels = new ArrayList<>();
            List<List<String>> firstDataRows = new ArrayList<>();
            String secondContent = "";
            List<String> secondColumnLabels = new ArrayList<>();
            List<List<String>> secondDataRows = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                switch (line.trim()) {
                    case "firstContent" -> firstContent = reader.readLine().trim();
                    case "firstColumns" -> firstColumnLabels = Arrays.asList(reader.readLine().split(","));
                    case "firstRows" -> {
                        while ((line = reader.readLine()) != null && !line.equals("secondContent")) {
                            firstDataRows.add(Arrays.asList(line.split(",")));
                        }
                    }
                    case "secondContent" -> secondContent = reader.readLine().trim();
                    case "secondColumns" -> secondColumnLabels = Arrays.asList(reader.readLine().split(","));
                    case "secondRows" -> {
                        while ((line = reader.readLine()) != null) {
                            secondDataRows.add(Arrays.asList(line.split(",")));
                        }
                    }
                }
            }

            return new CohortDoubleAnalysisRemainHeatmapResponseDto(
                    firstContent, firstColumnLabels, firstDataRows,
                    secondContent, secondColumnLabels, secondDataRows
            );
        } catch (Exception e) {
            throw new RuntimeException("이중 히트맵 분석 실패", e);
        }
    }

    @Override
    public CohortDoubleAnalysisVisualizationResponseDto fetchDoubleVisualization(CohortDoubleAnalysisVisualizationRequestDto requestDto) {
        try (BufferedReader reader = getReader("mock/double-visualization.csv")) {
            reader.readLine();
            String[] parts = reader.readLine().split(",");

            String firstImageBase64A = encodeBase64(parts[0].trim());
            String firstImageBase64B = encodeBase64(parts[1].trim());
            String secondImageBase64A = encodeBase64(parts[2].trim());
            String secondImageBase64B = encodeBase64(parts[3].trim());

            return new CohortDoubleAnalysisVisualizationResponseDto(
                    firstImageBase64A,
                    firstImageBase64B,
                    secondImageBase64A,
                    secondImageBase64B
            );
        } catch (Exception e) {
            throw new RuntimeException("이중 시각화 분석 실패", e);
        }
    }

    @Override
    public CohortDoubleAnalysisUserDataResponseDto fetchDoubleUserData(CohortDoubleAnalysisUserDataRequestDto requestDto) {
        try (BufferedReader reader = getReader("mock/double-user-data.csv")) {
            String[] headers = reader.readLine().split(",");
            List<Map<String, String>> firstTable = new ArrayList<>();
            List<Map<String, String>> secondTable = new ArrayList<>();
            String line;
            boolean readingFirst = true;

            while ((line = reader.readLine()) != null) {
                if (line.trim().equals("---")) {
                    readingFirst = false;
                    continue;
                }
                String[] values = line.split(",");
                Map<String, String> row = new LinkedHashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    if (readingFirst && requestDto.getFields().contains(headers[i].trim())) {
                        row.put(headers[i].trim(), values[i].trim());
                    } else if (!readingFirst && requestDto.getFields().contains(headers[i].trim())) {
                        row.put(headers[i].trim(), values[i].trim());
                    }
                }
                if (readingFirst) {
                    firstTable.add(row);
                } else {
                    secondTable.add(row);
                }
            }

            return new CohortDoubleAnalysisUserDataResponseDto(firstTable, secondTable);
        } catch (Exception e) {
            throw new RuntimeException("이중 유저 데이터 로딩 실패", e);
        }
    }

    private BufferedReader getReader(String resourcePath) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (stream == null) throw new IllegalArgumentException("CSV 파일 없음: " + resourcePath);
        return new BufferedReader(new InputStreamReader(stream));
    }

    private String encodeBase64(String imagePath) throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(imagePath);
        if (stream == null) throw new IllegalArgumentException("이미지 파일 없음: " + imagePath);
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(stream.readAllBytes());
    }
}