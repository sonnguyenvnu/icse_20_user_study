public static ActivityThread hook() throws Throwable {
  final XC_MethodHook methodHook=new SystemMain();
  final XC_MethodHook.MethodHookParam param=new XC_MethodHook.MethodHookParam();
  param.thisObject=null;
  param.args=new Object[]{};
  methodHook.callBeforeHookedMethod(param);
  if (!param.returnEarly) {
    param.setResult(backup());
  }
  methodHook.callAfterHookedMethod(param);
  return (ActivityThread)param.getResult();
}
