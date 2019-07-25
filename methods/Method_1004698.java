/** 
 * Resolve this instance according to the specified Spring Boot  {@link Version}. Return a  {@link Dependency} instance that has its state resolved against thespecified version.
 * @param bootVersion the Spring Boot version
 * @return this instance
 */
public Dependency resolve(Version bootVersion){
  for (  Mapping mapping : this.mappings) {
    if (mapping.range.match(bootVersion)) {
      Dependency dependency=new Dependency(this);
      dependency.groupId=(mapping.groupId != null) ? mapping.groupId : this.groupId;
      dependency.artifactId=(mapping.artifactId != null) ? mapping.artifactId : this.artifactId;
      dependency.version=(mapping.version != null) ? mapping.version : this.version;
      dependency.versionRequirement=mapping.range.toString();
      dependency.mappings=null;
      return dependency;
    }
  }
  return this;
}
