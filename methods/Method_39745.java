public static boolean isArgumentTypeMethod(final String name,final String desc){
  if (name.equals("argumentType")) {
    if (desc.equals("(I)Ljava/lang/Class;")) {
      return true;
    }
  }
  return false;
}
