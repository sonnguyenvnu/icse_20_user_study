public static boolean isInvokeMethod(final String name,final String desc){
  if (name.equals("invoke")) {
    if (desc.equals("()Ljava/lang/Object;")) {
      return true;
    }
  }
  return false;
}
