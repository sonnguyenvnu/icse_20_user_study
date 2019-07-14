private Object findTarget(Object item){
  Object current=item;
  while (current instanceof Advised) {
    try {
      current=((Advised)current).getTargetSource().getTarget();
    }
 catch (    Exception e) {
      ReflectionUtils.rethrowRuntimeException(e);
    }
  }
  return current;
}
