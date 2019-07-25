/** 
 * Parses repository definition.
 * @param repositoryDefinition repository definition
 */
public PutRepositoryRequest source(Map<String,Object> repositoryDefinition){
  for (  Map.Entry<String,Object> entry : repositoryDefinition.entrySet()) {
    String name=entry.getKey();
    if (name.equals("type")) {
      type(entry.getValue().toString());
    }
 else     if (name.equals("settings")) {
      if (!(entry.getValue() instanceof Map)) {
        throw new IllegalArgumentException("Malformed settings section, should include an inner object");
      }
      @SuppressWarnings("unchecked") Map<String,Object> sub=(Map<String,Object>)entry.getValue();
      settings(sub);
    }
  }
  return this;
}
