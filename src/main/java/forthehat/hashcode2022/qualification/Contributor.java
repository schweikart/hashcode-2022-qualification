package forthehat.hashcode2022.qualification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Contributor {
  public Project lastProject;

  private final String name;

  private final Map<Skill, Integer> skillLevels = new HashMap<>();

  public Contributor(String name, Set<Tuple<Skill, Integer>> initialSkillLevels) {
    this.name = name;
    initialSkillLevels.forEach(s -> skillLevels.put(s.first(), s.second()));
  }

  public String getName() {
    return name;
  }

  public int getSkillLevel(Skill skill) {
    Integer value = this.skillLevels.get(skill);
    return value == null ? 0 : value;
  }

  public void learn(Skill skill) {
    if (skillLevels.containsKey(skill)) {
      int oldLevel = skillLevels.get(skill);
      this.skillLevels.remove(skill);
      this.skillLevels.put(skill, oldLevel + 1);
    } else {
      this.skillLevels.put(skill, 1);
    }

  }
}
