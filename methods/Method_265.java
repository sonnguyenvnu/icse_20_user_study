private static boolean isListenerRequired(ExecutableElement element){
  return element.getAnnotation(Optional.class) == null;
}
