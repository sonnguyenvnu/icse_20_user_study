/** 
 * We use a generic method and not work on the real classes, because they all have the same behaviour. They call all get methods that are needed and use the return value. So this will prevent writing similar methods for every type. <p> In this method, all <Code>get</Code> methods that entryType has will be used and their value will be put to fields, if it is not null. So for example if entryType has the method <Code>getAbstract</Code>, then "abstract" will be put as key to fields and the value of <Code>getAbstract</Code> will be put as value to fields. Some <Code>get</Code> methods shouldn't be mapped to fields, so <Code>getClass</Code> for example will be skipped.
 * @param entryType This can be all possible BibTeX types. It contains all fields of the entry and their values.
 * @param fields A map where the name and the value of all fields that the entry contains will be put.
 */
private <T>void parse(T entryType,Map<String,String> fields){
  Method[] declaredMethods=entryType.getClass().getDeclaredMethods();
  for (  Method method : declaredMethods) {
    try {
      if (method.getName().equals("getYear")) {
        putYear(fields,(XMLGregorianCalendar)method.invoke(entryType));
        continue;
      }
 else       if (method.getName().equals("getNumber")) {
        putNumber(fields,(BigInteger)method.invoke(entryType));
        continue;
      }
 else       if (isMethodToIgnore(method.getName())) {
        continue;
      }
 else       if (method.getName().startsWith("get")) {
        putIfValueNotNull(fields,method.getName().replace("get",""),(String)method.invoke(entryType));
      }
    }
 catch (    IllegalArgumentException|InvocationTargetException|IllegalAccessException e) {
      LOGGER.error("Could not invoke method",e);
    }
  }
}
