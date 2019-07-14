private static void addMethodIfNotExist(final List<Method> allMethods,final Method newMethod){
  for (  Method m : allMethods) {
    if (compareSignatures(m,newMethod)) {
      return;
    }
  }
  allMethods.add(newMethod);
}
