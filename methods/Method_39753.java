public static boolean isTargetMethodSignatureMethod(final String name,final String desc){
  if (name.equals("targetMethodSignature")) {
    if (desc.equals("()Ljava/lang/String;")) {
      return true;
    }
  }
  return false;
}
