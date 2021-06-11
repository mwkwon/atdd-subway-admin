package nextstep.subway.lineStation.domain.wrappers;

import nextstep.subway.lineStation.domain.LineStation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("노선 지하철 연결 테이블 일급 컬렉션 객체 테스트")
public class LineStationsTest {

    @Test
    void 노선_지하철_연결_테이블_일급_컬렉션_객체_생성() {
        LineStations lineStations = new LineStations(Arrays.asList(new LineStation(2L, 1L, 10)));
        assertThat(lineStations).isEqualTo(new LineStations(Arrays.asList(new LineStation(2L, 1L, 10))));
    }

    @Test
    void 노선_지하철_연결_테이블_일급_컬렉션_객체_포함_여부() {
        LineStations lineStations = new LineStations(Arrays.asList(new LineStation(2L, 1L, 10)));
        LineStation lineStation = new LineStation(3L, 1L, 10);
        assertThat(lineStations.contains(new LineStation(2L, 1L, 10))).isTrue();
        assertThat(lineStations.contains(lineStation)).isFalse();
    }

    @Test
    void 노선_지하철_연결_테이블_일급_컬렉션_객체_추가() {
        LineStation lineStation1 = new LineStation(2L, 1L, 10);
        LineStation lineStation2 = new LineStation(3L, 2L, 100);
        List<LineStation> lineStations = new ArrayList<>();
        lineStations.add(lineStation1);
        LineStations actual = new LineStations(lineStations);

        actual.addLineStation(lineStation2);
        assertThat(actual).isEqualTo(new LineStations(Arrays.asList(lineStation1, lineStation2)));
    }

    @Test
    void 노선_지하철_연결_테이블_일급_컬렉션_중복_객체_추가() {
        LineStation lineStation1 = new LineStation(2L, 1L, 10);
        List<LineStation> lineStations = new ArrayList<>();
        lineStations.add(lineStation1);
        LineStations actual = new LineStations(lineStations);

        actual.addLineStation(lineStation1);
        assertThat(actual).isEqualTo(new LineStations(Arrays.asList(lineStation1)));
    }
}