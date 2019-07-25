/** 
 * Return the defaults for the capabilities defined on this instance.
 * @return the default capabilities
 */
public Map<String,Object> defaults(){
  Map<String,Object> defaults=new LinkedHashMap<>();
  defaults.put("type",defaultId(this.types));
  defaults.put("bootVersion",defaultId(this.bootVersions));
  defaults.put("packaging",defaultId(this.packagings));
  defaults.put("javaVersion",defaultId(this.javaVersions));
  defaults.put("language",defaultId(this.languages));
  defaults.put("groupId",this.groupId.getContent());
  defaults.put("artifactId",this.artifactId.getContent());
  defaults.put("version",this.version.getContent());
  defaults.put("name",this.name.getContent());
  defaults.put("description",this.description.getContent());
  defaults.put("packageName",this.packageName.getContent());
  return defaults;
}
