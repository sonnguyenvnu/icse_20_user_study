public List<Method> select(final Class<?> clazz){
  final Method[] methods=clazz.getMethods();
  final Set<String> sortedMethodNames=getSortedMethods(clazz,methods);
  final Map<String,Method> sortedMethods=new HashMap<>();
  final List<Method> unsortedMethods=new ArrayList<>();
  for (  final Method method : methods) {
    selectMethod(sortedMethodNames,sortedMethods,unsortedMethods,method);
  }
  for (  final String name : sortedMethodNames) {
    unsortedMethods.add(sortedMethods.get(name));
  }
  return unsortedMethods;
}
