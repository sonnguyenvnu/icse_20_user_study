static Object[] stackWalkGetTrace(){
  StackTraceElement[] stackTrace=Thread.currentThread().getStackTrace();
  int i=3;
  for (; i < stackTrace.length; i++) {
    if (!stackTrace[i].getClassName().startsWith("org.lwjgl.system.Memory")) {
      break;
    }
  }
  return Arrays.copyOfRange(stackTrace,i,stackTrace.length);
}
