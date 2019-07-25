public static boolean exempt(String... methods){
  if (sVmRuntime == null || setHiddenApiExemptions == null) {
    return false;
  }
  try {
    setHiddenApiExemptions.invoke(sVmRuntime,new Object[]{methods});
    return true;
  }
 catch (  Throwable e) {
    return false;
  }
}
