public static boolean isCreateArgumentsClassArrayMethod(final String name,final String desc){
  if (name.equals("createArgumentsClassArray")) {
    if (desc.equals("()[Ljava/lang/Class;")) {
      return true;
    }
  }
  return false;
}
