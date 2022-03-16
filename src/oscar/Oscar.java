package oscar;

import static java.lang.Integer.parseInt;

public class Oscar {
    Integer index;
    Integer year;
    Integer age;
    String name;
    String movie;

    public Oscar(Integer index, Integer year, Integer age, String name, String movie){
        this.index = index;
        this.year = year;
        this.age = age;
        this.name = name;
        this.movie = movie;
    }

    public static Oscar fromLine(String line){
        String[] split = line.split("; ");
        return new Oscar(
                parseInt(split[0]),
                parseInt(split[1]),
                parseInt(split[2]),
                split[3],
                split[4]
        );
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    @Override
    public String toString(){
        return "Oscar Awards{" +
                "index = " + index +
                "year = " + year +
                "age = " + age +
                "name = '" + movie + '\'' +
                "movie = '" + movie +'\'' +
                '}';
    }
}
