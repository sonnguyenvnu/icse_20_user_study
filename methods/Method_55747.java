static Object stackWalkGetMethod(Class<?> after){
  return StackWalker.getInstance().walk(s -> s.skip(2).filter(f -> !f.getClassName().startsWith(after.getName())).findFirst()).orElseThrow(IllegalStateException::new);
}
