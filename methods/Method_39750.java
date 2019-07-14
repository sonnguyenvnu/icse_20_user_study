public static boolean isReturnTypeMethod(final String name,final String desc){
  if (name.equals("returnType")) {
    if (desc.equals("()Ljava/lang/Class;")) {
      return true;
    }
  }
  return false;
}
