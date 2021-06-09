package nextstep.subway.section.domain;

import nextstep.subway.line.domain.Line;
import nextstep.subway.station.domain.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SectionTest {

    private Station upStation;
    private Station downStation;

    @BeforeEach
    void setUp() {
        upStation = new Station("강남역");
        downStation = new Station("광교역");
    }

    @Test
    @DisplayName("구간 생성")
    void create() {

        Section section = new Section(upStation, downStation, 100);
        assertThat(section).isEqualTo(new Section(upStation, downStation, 100));
    }

    @Test
    @DisplayName("구간 정보 객체에 line 객체 추가")
    void lineBy() {
        Section section = new Section(upStation, downStation, 100);
        section.lineBy(new Line("신분당선", "bg - red - 600"));
        assertThat(section)
                .isEqualTo(new Section(new Line("신분당선", "bg - red - 600"), upStation, downStation, 100));
    }

    @Test
    @DisplayName("상위 지하철역 계산")
    void calcUpStation() {
        Section section = new Section(upStation, downStation, 100);

        assertThat(section.calcUpStation(new Station("광교역"))).isEqualTo(new Station("강남역"));
        assertThat(section.calcUpStation(null)).isEqualTo(new Station("강남역"));
    }

    @Test
    @DisplayName("지하철역이 구간 정보에서 상행 지하철역인지 여부 확인")
    void isEqualUpStation() {
        Section section = new Section(upStation, downStation, 100);
        assertThat(section.isEqualUpStation(upStation)).isTrue();
        assertThat(section.isEqualUpStation(downStation)).isFalse();
    }
}
