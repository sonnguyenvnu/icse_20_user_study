public void process(ActionContext ac) throws Throwable {
  Object module=ac.getModule();
  Method method=ac.getMethod();
  Object[] args=ac.getMethodArgs();
  try {
    if (Mvcs.disableFastClassInvoker)     ac.setMethodReturn(method.invoke(module,args));
 else {
      _check(method);
      ac.setMethodReturn(fm.invoke(module,args));
    }
    doNext(ac);
  }
 catch (  IllegalAccessException e) {
    throw Lang.unwrapThrow(e);
  }
catch (  IllegalArgumentException e) {
    throw Lang.unwrapThrow(e);
  }
catch (  InvocationTargetException e) {
    throw e.getCause();
  }
}
