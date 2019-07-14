public static void put(Class<? extends ObjectFormatter> objectFormatter){
  try {
    formatterMap.put(objectFormatter.newInstance().clazz(),objectFormatter);
  }
 catch (  InstantiationException e) {
    throw new RuntimeException(e);
  }
catch (  IllegalAccessException e) {
    throw new RuntimeException(e);
  }
}
