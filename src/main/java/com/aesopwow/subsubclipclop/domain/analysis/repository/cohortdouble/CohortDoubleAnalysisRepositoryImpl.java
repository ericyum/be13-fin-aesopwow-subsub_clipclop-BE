package com.aesopwow.subsubclipclop.domain.analysis.repository.cohortdouble;

import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisInsightResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisRemainHeatmapResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisUserDataRequestDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisUserDataResponseDto;
import com.aesopwow.subsubclipclop.domain.analysis.dto.cohortdouble.CohortDoubleAnalysisVisualizationResponseDto;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CohortDoubleAnalysisRepositoryImpl implements CohortDoubleAnalysisRepository {

    @Override
    public CohortDoubleAnalysisInsightResponseDto fetchInsight(String clusterType) {
        try (BufferedReader reader = getReader("mock/double-insight.csv")) {
            reader.readLine(); // 헤더 skip
            String[] parts = reader.readLine().split(",");
            return new CohortDoubleAnalysisInsightResponseDto(parts[0].trim(), parts[1].trim());
        } catch (Exception e) {
            throw new RuntimeException("이중 인사이트 분석 실패", e);
        }
    }

    @Override
    public CohortDoubleAnalysisRemainHeatmapResponseDto fetchRemainHeatmap(String clusterType) {
        try (BufferedReader reader = getReader("mock/double-remain-heatmap.csv")) {
            String line;
            String title = "", content = "";
            List<String> columnLabels = new ArrayList<>();
            List<List<String>> dataRows = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                switch (line.trim()) {
                    case "title":
                        title = reader.readLine().trim();
                        break;
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
            return new CohortDoubleAnalysisRemainHeatmapResponseDto(title, content, columnLabels, dataRows);
        } catch (Exception e) {
            throw new RuntimeException("이중 히트맵 데이터 읽기 실패", e);
        }
    }

    @Override
    public CohortDoubleAnalysisVisualizationResponseDto fetchVisualization(String clusterType) {
        try (BufferedReader reader = getReader("mock/double-visualization.csv")) {
            reader.readLine(); // header skip
            String[] parts = reader.readLine().split(",");

            String title = parts[0].trim();
            String imageAPath = parts[1].trim();
            String imageBPath = parts[2].trim();

            String base64A = encodeBase64(imageAPath);
            String base64B = encodeBase64(imageBPath);

            return new CohortDoubleAnalysisVisualizationResponseDto(title, base64A, base64B);
        } catch (Exception e) {
            throw new RuntimeException("이중 시각화 분석 실패", e);
        }
    }

    @Override
    public CohortDoubleAnalysisUserDataResponseDto fetchUserData(CohortDoubleAnalysisUserDataRequestDto requestDto) {
        try (BufferedReader reader = getReader("mock/double-user-data.csv")) {
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
            return new CohortDoubleAnalysisUserDataResponseDto(table);
        } catch (Exception e) {
            throw new RuntimeException("이중 유저 데이터 로딩 실패", e);
        }
    }

    // ===== 헬퍼 메서드 =====
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