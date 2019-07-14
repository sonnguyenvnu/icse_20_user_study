public static boolean isCreateArgumentsArrayMethod(final String name,final String desc){
  if (name.equals("createArgumentsArray")) {
    if (desc.equals("()[Ljava/lang/Object;")) {
      return true;
    }
  }
  return false;
}
