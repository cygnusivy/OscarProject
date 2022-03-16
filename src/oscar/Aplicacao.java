package oscar;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Aplicacao {

    private ManipulacaoArquivo readerOscaMmale;
    private ManipulacaoArquivo readerOscarFemale;

    public Aplicacao() throws IOException{
        this.readerOscaMmale = new ManipulacaoArquivo("oscarmale.csv");
        this.readerOscarFemale = new ManipulacaoArquivo("oscarfemale.csv");

      //  System.out.println(this.readerOscarFemale.getOscarAwardList().size());
    }
    public static void main(String[] args) throws IOException{

        Aplicacao app = new Aplicacao();
        app.encontreAtorMaisJovem();
        app.encontreAtrizMaisPremiada();
        app.encontreJovemMaisPremiada();
        app.encontreOsAtoresEAtrizesComMaisDeUmPremio();
        app.buscaPorNome("Jodie Foster");
    }

    private void encontreAtorMaisJovem() {
        System.out.println("#1 Ator mair jovem:");
        this.readerOscaMmale.getOscarAwardList().stream()
                .min(Comparator.comparing(Oscar::getAge)).
                ifPresent(x -> System.out.printf("Ator mais jovem: " + x.getName() + ", Idade do ator: " + x.getAge() + ", Filme estrelado: " + x.getMovie() + ", Ano de lançamento: " + x.getYear() + "%n"));
    }

    private void encontreAtrizMaisPremiada() {
        System.out.println("#3 Atriz mais premiada: ");
        List<String> names = this.readerOscarFemale.getOscarAwardList().stream().map(Oscar::getName).collect(Collectors.toList());
        this.readerOscarFemale.getOscarAwardList().stream()
                .max(Comparator.comparingInt(o -> Collections.frequency(names, o.getName())))
                .ifPresent((o -> System.out.println(o.getName())));
    }

    private void encontreJovemMaisPremiada() {
    }

    private void encontreOsAtoresEAtrizesComMaisDeUmPremio() {
    }

    private void buscaPorNome(String name) {
        System.out.println("#5 Nome: " + name);
        List<Oscar> resultList = Stream.concat(
                this.readerOscarFemale.getOscarAwardList().stream(), this.readerOscaMmale.getOscarAwardList().stream())
                .filter(winner -> winner.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        if(resultList.isEmpty()){
            System.out.println("Resultado não encontrado");
        }
        else{
            System.out.printf("Nome: [%s], quantidade de Oscars: %d%n", name, resultList.size());
            resultList.forEach(r -> System.out.printf("%d, %d, %s%n", r.getYear(), r.getAge(), r.getMov ie()));
        }
    }









}

