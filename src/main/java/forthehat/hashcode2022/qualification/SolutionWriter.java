package forthehat.hashcode2022.qualification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SolutionWriter {
    public static void writeSolution(Path path, Solution solution) {
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
