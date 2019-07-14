static ConfigNamespace getJobRoot(String confRootName){
  String tokens[]=confRootName.split("#");
  String className=tokens[0];
  String fieldName=tokens[1];
  try {
    Field f=Class.forName(className).getField(fieldName);
    return (ConfigNamespace)f.get(null);
  }
 catch (  NoSuchFieldException|ClassNotFoundException|IllegalAccessException e) {
    throw new RuntimeException(e);
  }
}
