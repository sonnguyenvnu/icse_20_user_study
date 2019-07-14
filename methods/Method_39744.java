public static boolean isArgumentsCountMethod(final String name,final String desc){
  if (name.equals("argumentsCount")) {
    if (desc.equals("()I")) {
      return true;
    }
  }
  return false;
}
