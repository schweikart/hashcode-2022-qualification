package forthehat.hashcode2022.qualification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Contributor {

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
    return this.skillLevels.get(skill);
  }

  public void learn(Skill skill) {
    int oldLevel = skillLevels.get(skill);
    this.skillLevels.remove(skill);
    this.skillLevels.put(skill, oldLevel + 1);
  }
}
