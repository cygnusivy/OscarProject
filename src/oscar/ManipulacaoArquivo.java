package oscar;


import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.io.IOException;


public class ManipulacaoArquivo {

    private final List<Oscar> oscarAwardList; // ok

    public ManipulacaoArquivo(String fileName) throws IOException {
        this.oscarAwardList = this.preparaLeituraDoArquivo(fileName);
    }

    private List<Oscar> preparaLeituraDoArquivo(String fileName) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of(fileName))) {
            return lines.skip(1)
                    .map(Oscar::fromLine)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }
    public List<Oscar> getOscarAwardList(){
        return oscarAwardList;
    }
}

