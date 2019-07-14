public static boolean isReturnValueMethod(final String name,final String desc){
  if (name.equals("returnValue")) {
    if (desc.equals("(Ljava/lang/Object;)Ljava/lang/Object;")) {
      return true;
    }
  }
  return false;
}
