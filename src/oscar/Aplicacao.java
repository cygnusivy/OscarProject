package oscar;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Aplicacao {
    private static ManipulacaoArquivo oscarmale;
    private static ManipulacaoArquivo oscarfemale;
    private Oscar rosterOscar;

    public static void main(String[] args) {
        Aplicacao ap = new Aplicacao();

//        ap.ganhadorMaisJovem();
//        ap.ganhadoraMaisJovem();
//        ap.atrizMaisPremiada();
//        ap.atorMaisPremiado();
//        ap.atrizEntrevinteetrintaAnosMaispremiada();

        oscarmale = new ManipulacaoArquivo("oscarmale.csv");
        oscarfemale = new ManipulacaoArquivo("oscarfemale.csv");

    }

    private void ganhadorMaisJovem() {
        List<Oscar> roster = oscarmale.getRoster();
        System.out.println("Ganhador mais Jovem: " + roster.stream().sorted(Comparator.comparing(Oscar::getAge)).findFirst());
    }

    private void ganhadoraMaisJovem() {
        List<Oscar> roster = oscarfemale.getRoster();
        System.out.println("Ganhadora mais jovem: "+ roster.stream().sorted(Comparator.comparing(Oscar::getAge)).findFirst());
    }
    private void atrizMaisPremiada(){
        List<Oscar> roster = oscarfemale.getRoster();
        Map<String, Long> map = roster.stream()
                .map(Oscar::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        map.entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue)).ifPresent(c -> System.out.println(c.getKey() + c.getValue() ));
    }
    private void atorMaisPremiado(){
        List<Oscar> roster = oscarmale.getRoster();
        Map<String, Long> map = roster.stream()
                .map(Oscar::getName)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        map.entrySet().stream().max(Comparator.comparingLong(Map.Entry::getValue)).ifPresent(c -> System.out.println(c.getKey() + c.getValue() ));
    }
    private void atrizEntrevinteetrintaAnosMaispremiada() {
    System.out.println("Atriz entre mais premiada (entre 20 e 30 anos): ");
    List<Oscar> roster = oscarfemale.getRoster();
    Map<String, Long> map = roster.stream()
            .filter(OscarGanhadora -> (OscarGanhadora.getAge() >=20 && OscarGanhadora.getAge() <=30))
            .map(Oscar::getName)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    map.entrySet().stream().max(Comparator.comparing(Map.Entry::getValue)).ifPresent(i -> System.out.println(i.getKey() + i.getValue()));
    }
}