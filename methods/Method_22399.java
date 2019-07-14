protected void initInternalTool(String className){
  try {
    Class<?> toolClass=Class.forName(className);
    final Tool tool=(Tool)toolClass.getDeclaredConstructor().newInstance();
    tool.init(this);
    internalTools.add(tool);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
