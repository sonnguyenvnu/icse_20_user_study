static Object[] stackWalkGetTrace(){
  return StackWalker.getInstance().walk(s -> s.skip(2).dropWhile(f -> f.getClassName().startsWith("org.lwjgl.system.Memory")).toArray(StackWalker.StackFrame[]::new));
}
