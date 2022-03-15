package oscar;

import static java.lang.Integer.parseInt;

public class Oscar {
    Integer Index;
    Integer Year;
    Integer Age;
    String Name;
    String Movie;

    public Oscar(Integer Index, Integer Year, Integer Age, String Name, String Movie){
        this.Index = Index;
        this.Year = Year;
        this.Age = Age;
        this.Name = Name;
        this.Movie = Movie;
    }
    public Integer getIndex(){
        return Index;
    }
    public Integer getYear(){
        return Year;
    }
    public Integer getAge() {
        return Age;
    }
    public String getName(){
        return Name;
    }
    public String getMovie(){
        return Movie;
    }

    public static Oscar fromLine(String line){
        String[] split = line.split("; (?=\\S)");
        return new Oscar(
                parseInt(split[0]),
                parseInt(split[1]),
                parseInt(split[2]),
                split[3],
                split[4]
        );

    }
    @Override
    public String toString(){
        return "Oscar Awards{" +
                ", Ganhador: " + Name +
                ", Idade: " + Age +
                ", Ano da premiação: " + Year +
                ", Título da Obra: " + Movie + '\'' +
                '}';
    }
}
