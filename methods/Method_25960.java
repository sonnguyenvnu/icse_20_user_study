public static boolean isFirstConstructorOfMultiInjectedClass(Element injectedMember){
  if (injectedMember.getKind() == ElementKind.CONSTRUCTOR) {
    List<ExecutableElement> injectConstructors=getConstructorsWithAnnotations(injectedMember,Arrays.asList("javax.inject.Inject","com.google.inject.Inject"));
    if (injectConstructors.size() > 1 && injectConstructors.get(0).equals(injectedMember)) {
      return true;
    }
  }
  return false;
}
