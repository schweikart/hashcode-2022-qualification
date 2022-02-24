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

    solveProblem("a_an_example");
    solveProblem("b_better_start_small");
    solveProblem("c_collaboration");
    solveProblem("d_dense_schedule");
    solveProblem("e_exceptional_skills");
    solveProblem("f_find_great_mentors");
  }

  private static void solveProblem(String problemName) {
    Iterator<String> lineIterator = getLineIteratorForResource(String.format("%s.in.txt", problemName));
    Problem problem = new ProblemParser(lineIterator).parseProblem();

    Algorithm algorithm = new Algorithm(problem);

    Solution solution = algorithm.solve();
    SolutionWriter.writeSolution(Path.of(String.format("./out/%s.out.txt", problemName)), solution);
  }

  private static Iterator<String> getLineIteratorForResource(String resourceName) {
    InputStream inputStream = Main.class.getResourceAsStream(resourceName);
    Objects.requireNonNull(inputStream);

    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    return reader.lines().iterator();
  }
}
