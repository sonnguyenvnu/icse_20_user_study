public static boolean isTargetMethodAnnotationMethod(final String name,final String desc){
  if (name.equals("targetMethodAnnotation")) {
    if (desc.equals("(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;")) {
      return true;
    }
  }
  return false;
}
