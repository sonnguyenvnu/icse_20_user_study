public static boolean isInfoMethod(final String name,final String desc){
  if (name.equals("info")) {
    if (desc.equals("()Ljodd/proxetta/ProxyTargetInfo;")) {
      return true;
    }
  }
  return false;
}
