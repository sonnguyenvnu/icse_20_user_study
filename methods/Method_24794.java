private static void prepareConstantsList(){
  constantsMap=new TreeMap<>();
  Class<DefaultProblem> probClass=DefaultProblem.class;
  Field f[]=probClass.getFields();
  for (  Field field : f) {
    if (Modifier.isStatic(field.getModifiers()))     try {
      if (DEBUG) {
        Messages.log(field.getName() + " :" + field.get(null));
      }
      Object val=field.get(null);
      if (val instanceof Integer) {
        constantsMap.put((Integer)(val),field.getName());
      }
    }
 catch (    Exception e) {
      e.printStackTrace();
      break;
    }
  }
  if (DEBUG) {
    Messages.log("Total items: " + constantsMap.size());
  }
}
