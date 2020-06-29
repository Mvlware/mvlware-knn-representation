import java.util.*;

public class KNNApplication {
    //статический блок прогружается в память до компиляции - двумерный массив double входных данных
    static double[][] instances = {{0.35,0.91,0.86,0.42,0.71}, {0.21,0.12,0.76,0.22,0.92}, {0.41,0.58,0.73,0.21,0.09},
                                {0.71,0.34,0.55,0.19,0.80}, {0.79,0.45,0.79,0.21,0.44}, {0.61,0.37,0.34,0.81,0.42},
                                {0.78,0.12,0.31,0.83,0.87}, {0.52,0.23,0.73,0.45,0.78}, {0.53,0.17,0.63,0.29,0.72},};
    //метод возращает наиболее приоритетное значение для нового класса из строкового массива
    public static String findMajorityClass(String[] array) {
        //входной массив копируется в hash карту, гарантирующую уникальность ключей
        HashMap<String, Integer> map = new HashMap<>();
        for (String arg : array) { map.merge(arg, 1, Integer::sum); }
        //промежуточный вывод (необязательно)
        StringBuilder print1 = new StringBuilder();
        map.forEach((key, value) -> print1.append("Встречаемость " + key + " : " + value + ", "));
        System.out.println(print1.substring(0, print1.length() - 2) + ".");
        Integer[] counts = map.values().toArray(new Integer[0]);
        String[] uniqueValues = map.keySet().toArray(new String[0]);
        //находим частоту максимального значения количества в массиве counts - это показывает сколько раз встречается класс
        int max = Collections.max(Arrays.asList(counts));
        //промежуточный вывод (необязательно)
        System.out.println("Макс. встречаемость: "+ max);
        //частота
        int freq = 0;
        for (int count : counts) {
            if (count == max) {
                freq++;
            }
        }
        //если частота == 1 (один город), то у нас есть только один приоритетный класс и нам нужно получить его индекс
        int index = 0;
        if (freq == 1) {
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] == max) {
                    index = i;
                    break;
                }
            }
            return uniqueValues[index];
            //во всех остальных случаях - получаем рандомный приоритетный класс
        } else {
            int[] mass = new int[freq]; //создаем массив размерной частоты
            StringBuilder print = new StringBuilder();
            for (int i = 0; i < counts.length; i++) {
                if (counts[i] == max) {
                    mass[i] = i;
                    print.append("Индекс класса : " + String.valueOf(i) + ", ");
                }
            }
            System.out.println(print.substring(0, print.length() - 2) + ".");
            //ранодомный генератор
            Random generator = new Random();
            //получаем рандомный индекс 0 <= rIndex < mass
            int rIndex = generator.nextInt(mass.length);
            System.out.println("Индекс приоритеного класса : " + rIndex);
            return uniqueValues[rIndex];
        }
    }

    public static void main(String[] args) {
        int k = 6; //количество соседей (объектов)
        List<City> cityList = new ArrayList<>(); //список для сохранения городов
        List<Result> resultList = new ArrayList<>(); //список для сохранения городов - результат расстояния
        cityList.add(new City(instances[0], "Санкт-Петербург"));
        cityList.add(new City(instances[1], "Москва"));
        cityList.add(new City(instances[2], "Москва"));
        cityList.add(new City(instances[3], "Иркутск"));
        cityList.add(new City(instances[4], "Ярославль"));
        cityList.add(new City(instances[5], "Казань"));
        cityList.add(new City(instances[6], "Владивосток"));
        cityList.add(new City(instances[7], "Санкт-Петербург"));
        cityList.add(new City(instances[8], "Москва"));
        double[] query = {0.65, 0.78, 0.21, 0.29, 0.58}; //массив входных данных о неизвестном городе
        for (City city : cityList) { //нахождение расстояния
            double dist = 0.0;
            for (int j = 0; j < city.cityAttributes.length; j++) {
                dist += Math.pow(city.cityAttributes[j] - query[j], 2);
            }
            double distance = Math.sqrt(dist);
            resultList.add(new Result(distance, city.cityName));
        }
        //сортировка
        resultList.sort(new DistanceComparator());
        String[] ss = new String[k];
        for (int x = 0; x < k; x++) {
            System.out.println(resultList.get(x).cityName + " .... " + resultList.get(x).distance);
            //получаем классы k ближайших объектов (городов) и сохраняем в массив
            ss[x] = resultList.get(x).cityName;
        }
        //вывод приоритетного класса
        System.out.println("Класс нового объекта: " + findMajorityClass(ss));
    }
}
