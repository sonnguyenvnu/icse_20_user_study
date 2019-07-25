public static boolean support(){
  return MAX_STUB_ARGS > 0 && SandHook.canGetObject() && SandHook.canGetObjectAddress();
}
