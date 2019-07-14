static public ProjectMetadata createProjectMetadata(ObjectNode optionObj){
  ProjectMetadata pm=new ProjectMetadata();
  pm.setName(JSONUtilities.getString(optionObj,"projectName","Untitled"));
  pm.setTags(JSONUtilities.getStringArray(optionObj,"projectTags"));
  String encoding=JSONUtilities.getString(optionObj,"encoding","UTF-8");
  if ("".equals(encoding)) {
    encoding="UTF-8";
  }
  pm.setEncoding(encoding);
  return pm;
}
