public static boolean isTargetMethodDescriptionMethod(final String name,final String desc){
  if (name.equals("targetMethodDescription")) {
    if (desc.equals("()Ljava/lang/String;")) {
      return true;
    }
  }
  return false;
}
