package oscar;


import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.io.IOException;


public class ManipulacaoArquivo {

    private final List<Oscar> roster;

    public ManipulacaoArquivo(String fileName) {

        this.roster = leiaArquivo(fileName);
    }

    private List<Oscar> leiaArquivo(String fileName) {
        try (Stream<String> fileLine = Files.lines(Paths.get(fileName))) {
            return fileLine
                    .skip(1)
                    .map(Oscar::fromLine)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }
    public List<Oscar> getRoster(){
        return roster;
    }
}

