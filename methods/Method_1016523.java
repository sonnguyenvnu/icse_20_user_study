public static Set<ExecutableElement> only(Modifier modifier,Set<ExecutableElement> methods){
  ImmutableSet.Builder<ExecutableElement> result=ImmutableSet.builder();
  for (  ExecutableElement method : methods) {
    if (method.getModifiers().contains(modifier)) {
      result.add(method);
    }
  }
  return result.build();
}
