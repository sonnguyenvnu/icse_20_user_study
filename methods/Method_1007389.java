private static void error(Class<?> clazz,String name,String desc){
  throw new RuntimeException("not found " + name + ":" + desc + " in " + clazz.getName());
}
