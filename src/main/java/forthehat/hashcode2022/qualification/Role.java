package forthehat.hashcode2022.qualification;

public class Role {

  public final Skill skill;
  public final int requiredLevel;

  public Contributor contributor;

  public Role(Skill skill, int requiredLevel) {
    this(null, skill, requiredLevel);
  }

  public Role(Contributor contributor, Skill skill, int requiredLevel) {
    this.contributor = contributor;
    this.skill = skill;
    this.requiredLevel = requiredLevel;
  }
}
