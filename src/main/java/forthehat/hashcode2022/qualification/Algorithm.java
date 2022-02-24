package forthehat.hashcode2022.qualification;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Algorithm {
  private Project[] projects;
  private final Contributor[] contributors;

  private double averageScore;
  private double averageDeadline;
  private double averageDuration;
  private double averageRoleAmount;
  private double averageSkillLevel;

  public Algorithm(Problem problem) {
    this.projects = problem.projects();
    this.contributors = problem.contributors();
  }

  public Solution solve() {
    calculateAverages();
    sortProjects();

    int lastDay = 0;
    List<Project> doableProjects = new LinkedList<>();
    for (int i = 0; i < projects.length; i++) {
      Project project = projects[i];
      if (assignContributors(project)) {
        doableProjects.add(project);
        lastDay += project.getDuration(); // check best before

        for (int r = 0; r < project.getRoles().length; r++) {
          Role role = project.getRoles()[r];
          if (role.contributor.getSkillLevel(role.skill) <= role.requiredLevel) {
            role.contributor.learn(role.skill);
          }
        }
      }
    }

    return new Solution(doableProjects);
  }

  private boolean assignContributors(Project project) {
    for (int r = 0; r < project.getRoles().length; r++) {
      Role role = project.getRoles()[r];
      int requiredSkillLevel = role.requiredLevel;

      int bestC = -1;
      int bestSkillLevel = Integer.MAX_VALUE;
      for (int c = 0; c < this.contributors.length && bestSkillLevel > requiredSkillLevel; c++) {
        Contributor contributor = this.contributors[c];
        int cSkillLevel = contributor.getSkillLevel(role.skill);
        if (contributor.lastProject != project && cSkillLevel >= requiredSkillLevel) {
          if (bestC == -1 || cSkillLevel < bestSkillLevel) {
            bestC = c;
            bestSkillLevel = cSkillLevel;
          }
        }
      }

      if (bestC != -1) {
        // assign contributor c to role r
        Contributor contributor = this.contributors[bestC];
        role.contributor = contributor;
        contributor.lastProject = project;
      } else {
        return false;
      }
    }
    return true;
  }

  private void calculateAverages() {
    long scoreSum = 0;
    long deadlineSum = 0;
    long durationSum = 0;
    long roleAmountSum = 0;
    long skillLevelSum = 0;
    for (var project : projects) {
      scoreSum += project.getScore();
      deadlineSum += project.getBestBeforeDate();
      durationSum += project.getDuration();
      roleAmountSum += project.getRoles().length;
      var projectSkillLevelSum = 0;
      for (var role : project.getRoles()) {
        projectSkillLevelSum += role.requiredLevel;
      }
      project.skillLevelAverage = ((double) projectSkillLevelSum) / project.getRoles().length;
      skillLevelSum += projectSkillLevelSum;
    }

    this.averageScore = ((double) scoreSum) / this.projects.length;
    this.averageDeadline = ((double) deadlineSum) / this.projects.length;
    this.averageDuration = ((double) durationSum) / this.projects.length;
    this.averageRoleAmount = ((double) roleAmountSum) / this.projects.length;
    this.averageSkillLevel = ((double) skillLevelSum) / this.projects.length;
  }

  private void sortProjects() {
    for (int i = 0; i < projects.length; i++) {
      this.projects[i].value = calculateValue(projects[i]);
    }
    Arrays.sort(projects, Comparator.comparingDouble(p -> p.value));
  }

  private double calculateValue(Project project) {
    double scoreValue = project.getScore() / this.averageScore;
    double deadlineValue =  project.getBestBeforeDate() / this.averageDeadline; // high -> later
    double durationValue = project.getDuration() / this.averageDuration; // low is better
    double roleAmountValue = this.averageRoleAmount / project.getRoles().length; // low is better
    double skillLevelValue = this.averageSkillLevel / project.skillLevelAverage; // low is better

    return scoreValue * deadlineValue * durationValue * roleAmountValue * skillLevelValue;
  }
}
