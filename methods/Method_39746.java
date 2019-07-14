public static boolean isArgumentMethod(final String name,final String desc){
  if (name.equals("argument")) {
    if (desc.equals("(I)Ljava/lang/Object;")) {
      return true;
    }
  }
  return false;
}
