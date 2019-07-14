public static boolean isTargetClassAnnotationMethod(final String name,final String desc){
  if (name.equals("targetClassAnnotation")) {
    if (desc.equals("(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;")) {
      return true;
    }
  }
  return false;
}
