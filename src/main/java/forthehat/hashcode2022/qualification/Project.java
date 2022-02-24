package forthehat.hashcode2022.qualification;

import java.util.HashMap;
import java.util.Map;

public class Project {

  private final String name;

  private final int duration;

  private final int score;

  private final int bestBeforeDate;

  private final Map<Skill, Integer> skillIndexMap = new HashMap<>();

  private final Role[] roles;

  public Project(String name, int duration, int score, int bestBeforeDate, Role[] roles) {
    this.name = name;
    this.duration = duration;
    this.score = score;
    this.bestBeforeDate = bestBeforeDate;
    this.roles = roles;
  }

  public String getName() {
    return name;
  }

  public int getDuration() {
    return duration;
  }

  public int getScore() {
    return score;
  }

  public int getBestBeforeDate() {
    return bestBeforeDate;
  }

  public Role[] getRoles() {
    return this.roles;
  }

  @Override
  public String toString() {
    return String.format("%s\n%s\n", this.name, getStringRep(this.roles));
  }

  private String getStringRep(Role[] roles) {
    StringBuilder stringRepBuilder = new StringBuilder();
    for (Role role : roles) {
      stringRepBuilder.append(String.format("%s ", role.contributor.getName()));
    }
    var stringRep = stringRepBuilder.toString();
    return stringRep.substring(0, stringRep.length() - 1);
  }
}
