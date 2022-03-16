package oscar;

import java.io.IOException;
import java.util.*;
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
        app.encontreJovemMaisPremiadaEntreVinteETrintaAnos();
        app.encontreOsAtoresEAtrizesComMaisDeUmPremio();
        app.buscaPorNome("Jodie Foster");
    }

    private void encontreAtorMaisJovem() {
        System.out.println(" Ator mair jovem:");
        this.readerOscaMmale.getOscarAwardList().stream()
                .min(Comparator.comparing(Oscar::getAge)).
                ifPresent(x -> System.out.printf("Ator mais jovem: " + x.getName() + ", Idade do ator: " + x.getAge() + ", Filme estrelado: " + x.getMovie() + ", Ano de lançamento: " + x.getYear() + "%n"));
    }

    private void encontreAtrizMaisPremiada() {
        System.out.println("Atriz mais premiada: ");
        List<String> names = this.readerOscarFemale.getOscarAwardList().stream().map(Oscar::getName).collect(Collectors.toList());
        this.readerOscarFemale.getOscarAwardList().stream()
                .max(Comparator.comparingInt(o -> Collections.frequency(names, o.getName())))
                .ifPresent((o -> System.out.println(o.getName())));
    }

    private void encontreJovemMaisPremiadaEntreVinteETrintaAnos() {
        System.out.println("Atriz entre 20 e 30 anos mais premiada");
        Map<String, Long> mapNomeQuantidade = this.readerOscarFemale.getOscarAwardList().stream()
                .filter(o -> o.getAge() >= 20 && o.getAge() <= 30)
                .collect(Collectors.groupingBy(Oscar::getName, Collectors.counting()));
        mapNomeQuantidade
                .entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue))
                .ifPresent(e -> mapNomeQuantidade.entrySet().stream()
                        .filter(e1 -> e1.getValue().equals(e.getValue()))
                        .forEach(e1 -> System.out.printf("%s - %d%n", e1.getKey(), e1.getValue())));
    }

    private void encontreOsAtoresEAtrizesComMaisDeUmPremio() {
        System.out.println("Atores/atrizes com mais de um prêmio: ");
        List<String> todosNomes = new ArrayList<>();
        todosNomes.addAll(this.readerOscarFemale.getOscarAwardList().stream().map(Oscar::getName).collect(Collectors.toList()));
        todosNomes.addAll(this.readerOscaMmale.getOscarAwardList().stream().map(Oscar::getName).collect(Collectors.toList()));
        todosNomes.stream()
                .filter(name -> Collections.frequency(todosNomes, name) >= 2)
                .distinct()
                .forEach(System.out::println);
    }

    private void buscaPorNome(String name) {
        System.out.println("Nome: " + name);
        List<Oscar> resultList = Stream.concat(
                this.readerOscarFemale.getOscarAwardList().stream(), this.readerOscaMmale.getOscarAwardList().stream())
                .filter(winner -> winner.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        if(resultList.isEmpty()){
            System.out.println("Resultado não encontrado");
        }
        else{
            System.out.printf("Nome: [%s], quantidade de Oscars: %d%n", name, resultList.size());
            resultList.forEach(r -> System.out.printf("%d, %d, %s%n", r.getYear(), r.getAge(), r.getMovie()));
        }
    }
}

