public static boolean isTargetMethod(final String name,final String desc){
  if (name.equals("target")) {
    if (desc.equals("()Ljava/lang/Object;")) {
      return true;
    }
  }
  return false;
}
