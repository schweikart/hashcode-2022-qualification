package forthehat.hashcode2022.qualification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Project {

  private final String name;

  private final int duration;

  private final int score;

  private final int bestBeforeDate;

  private final List<Requirement> requirements = new LinkedList<>();

  private final Map<Skill, Integer> skillIndexMap = new HashMap<>();

  private Role[] roles;

  public Project(String name, int duration, int score, int bestBeforeDate,
                 List<Requirement> requirements) {
    this.name = name;
    this.duration = duration;
    this.score = score;
    this.bestBeforeDate = bestBeforeDate;
    this.requirements.addAll(requirements);
    var requirementsCount = requirements.size();
    for (int i = 0; i < requirementsCount; i++) {
      this.skillIndexMap.put(requirements.get(i).skill(), i);
    }
    this.roles = new Role[requirementsCount];
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

  public int getRequiredSkillLevel(Skill skill) {
    var optionalRequirement = this.requirements.stream()
        .filter(requirement -> requirement.skill().equals(skill))
        .findFirst();
    return optionalRequirement.map(Requirement::level).orElse(-1);
  }

  public void removeRole(Role role) {
    this.roles[skillIndexMap.get(role.skill())] = null;
  }

  public void addRole(Role role) {
    var skill = role.skill();
    this.roles[skillIndexMap.get(skill)] = role;
  }

  public Role[] getRoles() {
    return this.roles.clone();
  }

  @Override
  public String toString() {
    return String.format("%s\n %s\n", this.name, getStringRep(this.roles));
  }

  private String getStringRep(Role[] roles) {
    StringBuilder stringRepBuilder = new StringBuilder(this.name + "\n");
    for (Role role : roles) {
      stringRepBuilder.append(String.format("%s ", role.contributor().getName()));
    }
    var stringRep = stringRepBuilder.toString();
    return stringRep.substring(0, stringRep.length() - 1);
  }
}
