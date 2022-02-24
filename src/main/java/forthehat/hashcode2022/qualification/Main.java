package forthehat.hashcode2022.qualification;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class Main {
  public static void main(String[] args) {
    System.out.println("FOR THE HAT!");
  }

  private static void solveProblem(String problemName) {
    Iterator<String> lineIterator = getLineIteratorForResource(String.format("%s.in.txt", problemName));
    new ProblemParser(lineIterator).parseProblem();

    //SolutionWriter.writeSolution(Path.of(String.format("./out/%s.out.txt", problemName)), new Selection(List.of(), List.of()));
  }

  private static Iterator<String> getLineIteratorForResource(String resourceName) {
    InputStream inputStream = Main.class.getResourceAsStream(resourceName);
    Objects.requireNonNull(inputStream);

    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    return reader.lines().iterator();
  }
}
