@HookMethod("inCompatConfigList") public static boolean hook(int type,String packageName) throws Throwable {
  final XC_MethodHook methodHook=new OneplusWorkaround();
  final XC_MethodHook.MethodHookParam param=new XC_MethodHook.MethodHookParam();
  param.thisObject=null;
  param.args=new Object[]{type,packageName};
  methodHook.callBeforeHookedMethod(param);
  if (!param.returnEarly) {
    param.setResult(backup(type,packageName));
  }
  methodHook.callAfterHookedMethod(param);
  return (boolean)param.getResult();
}
