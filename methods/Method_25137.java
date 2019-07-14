private static boolean isSuppressWarnings(Class<? extends Annotation> annotation){
  return annotation.getSimpleName().equals("SuppressWarnings");
}
