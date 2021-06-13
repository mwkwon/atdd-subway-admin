package nextstep.subway.wrappers;

import nextstep.subway.line.domain.Line;
import nextstep.subway.lineStation.domain.LineStation;
import nextstep.subway.station.domain.Station;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.*;

@Embeddable
public class LineStations {

    @OneToMany(mappedBy = "line", cascade = CascadeType.ALL)
    private List<LineStation> lineStations = new ArrayList<>();

    public LineStations() {
    }

    public LineStations(List<LineStation> lineStations) {
        this.lineStations = lineStations;
    }

    public void addLineStation(LineStation lineStation) {
        if (!contains(lineStation)) {
            lineStations.add(lineStation);
        }
    }

    public boolean contains(LineStation lineStation) {
        return lineStations.contains(lineStation);
    }

    public void addLine(Line line) {
        for (LineStation lineStation : lineStations) {
            lineStation.lineBy(line);
        }
    }

    public List<LineStation> getLineStationsOrderByAsc() {
        List<LineStation> lineStations = new LinkedList<>();
        Optional<LineStation> preLineStation = findFirstLineStation();
        while (preLineStation.isPresent()) {
            LineStation lineStation = preLineStation.get();
            lineStations.add(lineStation);
            preLineStation = findNextLineStation(lineStation);
        }
        return lineStations;
    }

    public LineStation findLineStationByPreStation(Station preStation) {
        return lineStations
                .stream()
                .filter(ls -> ls.isSamePreStation(preStation))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public void updateFirstLineStation(LineStation lineStation) {
        findFirstLineStation().ifPresent(
                ls -> ls.update(lineStation.getStation(), lineStation.getPreStation(), lineStation.getDistance()));
    }

    private Optional<LineStation> findFirstLineStation() {
        return lineStations.stream().filter(LineStation::isNullPreStation).findFirst();
    }

    private Optional<LineStation> findNextLineStation(LineStation lineStation) {
        return lineStations
                .stream()
                .filter(ls -> ls.isNextLineStation(lineStation))
                .findFirst();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        LineStations that = (LineStations) object;
        return Objects.equals(lineStations, that.lineStations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineStations);
    }
}