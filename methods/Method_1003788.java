public T create(String scriptName,Path scriptPath,String scriptText,Object... scriptConstructionArgs) throws IllegalAccessException, InstantiationException {
  Class<T> scriptClass=compile(scriptName,scriptPath,scriptText);
  return DefaultGroovyMethods.newInstance(scriptClass,scriptConstructionArgs);
}
