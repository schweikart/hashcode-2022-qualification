package forthehat.hashcode2022.qualification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SolutionWriter {
    public static void writeSolution(Path path, Solution solution) {
        /*
        //size = firstLine, each project = 2 lines (name, contributors)
        List<String> contents = new ArrayList<>(1+ 2* solution.projectList.size());
        contents.add(String.valueOf(solution.projectList.size()) + "\n");

        /*
        for (var ingredient : selection.ingredients()) {
            contents.add(ingredient.name());
        }
         */

        if (!Files.isDirectory(path.getParent())) {
            try {
                Files.createDirectory(path.getParent());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            Files.writeString(path, solution.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
