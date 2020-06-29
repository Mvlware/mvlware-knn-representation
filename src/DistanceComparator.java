import java.util.Comparator;

public class DistanceComparator implements Comparator<Result> {
    //класс сравнения, используемый для сравнения результатов с помощью расстояний
    @Override
    public int compare(Result a, Result b) {
        return Double.compare(a.distance, b.distance);
    }
}
