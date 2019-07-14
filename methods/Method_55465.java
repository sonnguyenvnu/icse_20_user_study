public static String __stdcall(String signature){
  return Platform.get() == Platform.WINDOWS && Pointer.BITS32 ? "_s" + signature : signature;
}
