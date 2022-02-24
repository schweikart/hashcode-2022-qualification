package forthehat.hashcode2022.qualification;

import java.util.LinkedList;
import java.util.List;

public class Solution {

  private final List<Project> projects = new LinkedList<>();

  public Solution() {
  }

  public Solution(List<Project> projects) {
    this.projects.addAll(projects);
  }

  @Override
  public String toString() {
    StringBuilder stringRepBuilder = new StringBuilder(projects.size() + "\n");
    for (Project project : projects) {
      stringRepBuilder.append(project.toString());
    }
    var stringRep = stringRepBuilder.toString();
    return stringRep.substring(0, stringRep.length() - 1);
  }
}
