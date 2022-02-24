package forthehat.hashcode2022.qualification;

import java.util.*;

public record ProblemParser(Iterator<String> inputLineIterator) {

    public ProblemParser(Iterator<String> inputLineIterator) {
        this.inputLineIterator = Objects.requireNonNull(inputLineIterator);
    }

    public Problem parseProblem() {
        String[] amounts = inputLineIterator.next().trim().split("\\s+");
        int contributorsAmount = Integer.parseInt(amounts[0]);
        int projectAmount = Integer.parseInt(amounts[1]);
        var contributorArray = new Contributor[contributorsAmount];
        var projectArray = new Project[projectAmount];

        //all contributors
        for (int i = 0; i < contributorsAmount; i++) {
            String[] contributorLine = inputLineIterator.next().trim().split("\\s+");
            String name = contributorLine[0];
            int skillAmount = Integer.parseInt(contributorLine[1]);
            Set<Tuple<Skill, Integer>> skillSet = new HashSet<>();

            //parse skills of contributor
            for (int j = 0; j < skillAmount; j++) {
                String[] skill = inputLineIterator.next().trim().split("\\s+");
                Tuple<Skill, Integer> skillTuple =
                    new Tuple<>(new Skill(skill[0]), Integer.parseInt(skill[1]));
                skillSet.add(skillTuple);
            }
            contributorArray[i] = new Contributor(name, skillSet);
        }

        //all projects
        for (int i = 0; i < projectAmount; i++) {
            String[] projectLine = inputLineIterator.next().trim().split("\\s+");
            String name = projectLine[0];
            int duration = Integer.parseInt(projectLine[1]);
            int score = Integer.parseInt(projectLine[2]);
            int bestBefore = Integer.parseInt(projectLine[3]);
            int roleAmount = Integer.parseInt(projectLine[4]);
            Role[] roles = new Role[roleAmount];

            //parse roles of project
            for (int j = 0; j < roleAmount; j++) {
                String[] roleString = inputLineIterator.next().trim().split("\\s+");

                var role = new Role(new Skill(roleString[0]), Integer.parseInt(roleString[1]));
                roles[j] = role;
            }

            projectArray[i] = new Project(name, duration, score, bestBefore, roles);
        }

        if (inputLineIterator.hasNext()) {
            throw new RuntimeException("input is too long!");
        }

        System.out.printf("Parsed problem with %d contributors and %d different projects%n",
            contributorArray.length, projectArray.length);
        return new Problem(contributorArray, projectArray);
    }
}
