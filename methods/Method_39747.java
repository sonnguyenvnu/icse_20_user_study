public static boolean isSetArgumentMethod(final String name,final String desc){
  if (name.equals("setArgument")) {
    if (desc.equals("(Ljava/lang/Object;I)V")) {
      return true;
    }
  }
  return false;
}
