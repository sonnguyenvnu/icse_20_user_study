public static boolean isTargetClassMethod(final String name,final String desc){
  if (name.equals("targetClass")) {
    if (desc.equals("()Ljava/lang/Class;")) {
      return true;
    }
  }
  return false;
}
