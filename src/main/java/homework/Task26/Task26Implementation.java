package homework.Task26;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.function.BiFunction;

public class Task26Implementation implements Task26{

    @Override
    public Set<I2DPoint> analyze(Set<ISegment> segments) {

        List<ISegment> segmentList = new ArrayList<>(segments);
        TreeMap<Double, Set<I2DPoint>> intersectionMap = new TreeMap<>();

        for (int i = 0; i < segments.size(); i++) {
            ListIterator<ISegment> iterator = segmentList.listIterator(i);
            ISegment tempSegment = iterator.next();
            while(iterator.hasNext()) {
                I2DPoint intersectionPoint = intersection(tempSegment, iterator.next());
                if (intersectionPoint != null) {
                    HashSet<I2DPoint> defaultSet = new HashSet<>(Collections.singleton(intersectionPoint));
                    intersectionMap.merge(intersectionPoint.getX(), defaultSet, mergeSets());
                }
            }
        }
        return intersectionMap.firstEntry().getValue();
    }

    private BiFunction<Set<I2DPoint>, Set<I2DPoint>, Set<I2DPoint>> mergeSets() {
        return (v1, v2) -> {
            v1.add(v2.iterator().next());
            return v1;
        };
    }

    private static I2DPoint intersection(ISegment firstSegment, ISegment secondSegment) {

        I2DPoint resultPoint = null;

        Line line1 = new Line(firstSegment);
        Line line2 = new Line(secondSegment);

        double k_1 = line1.getK();
        double k_2 = line2.getK();
        double b_1 = line1.getB();
        double b_2 = line2.getB();

        if (Double.compare(k_2, k_1) != 0) {
            double x = - (b_2 - b_1) / (k_2 - k_1);
            Point2D point = new Point2D(x, k_1 * x + b_1);
            if (isContained(point, firstSegment)) {
                resultPoint = point;
            }
        } else {
            if (Double.compare(b_1, b_2) == 0) {
                Comparator<ISegment> segmentComparator = (o1, o2) -> pointComparator().compare(leftPointOfSegment(o1), leftPointOfSegment(o2));
                ISegment[] segments = {firstSegment, secondSegment};
                Arrays.sort(segments, segmentComparator);
                I2DPoint leftPointOfRightSegment = leftPointOfSegment(segments[1]);
                if (isContained(leftPointOfRightSegment, segments[0])) {
                    resultPoint = leftPointOfRightSegment;
                }
            }
        }
        return resultPoint;
    }

    private static boolean isContained(I2DPoint point, ISegment segment) {
        I2DPoint[] points = {point, segment.first(), segment.second()};
        Arrays.sort(points, pointComparator());
        return pointComparator().compare(points[1], point) == 0;
    }

    private static Comparator<I2DPoint> pointComparator() {
        return Comparator.comparingDouble(I2DPoint::getX).thenComparingDouble(I2DPoint::getY);
    }

    private static I2DPoint leftPointOfSegment(ISegment segment) {
        return pointComparator().compare(segment.first(), segment.second()) < 0 ? segment.first() : segment.second();
    }
}

@RequiredArgsConstructor
@Getter
class Point2D implements Task26.I2DPoint {
    private final double x;
    private final double y;
}

@Getter
class Line {
    private final double k;
    private final double b;

    Line(Task26.I2DPoint first, Task26.I2DPoint second) {
        k = (second.getY() - first.getY()) / (second.getX() - first.getX());
        b = - k * first.getX();
    }

    Line(Task26.ISegment segment) {
        Task26.I2DPoint first = segment.first();
        Task26.I2DPoint second = segment.second();
        k = (second.getY() - first.getY()) / (second.getX() - first.getX());
        b = - k * first.getX();
    }
}
