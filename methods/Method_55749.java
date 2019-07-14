static Object stackWalkCheckPop(Class<?> after,Object pushedObj){
  return StackWalker.getInstance().walk(s -> {
    Iterator<StackWalker.StackFrame> iter=s.skip(2).dropWhile(f -> f.getClassName().startsWith(after.getName())).iterator();
    if (iter.hasNext()) {
      StackWalker.StackFrame element=iter.next();
      StackWalker.StackFrame pushed=(StackWalker.StackFrame)pushedObj;
      if (isSameMethod(element,pushed)) {
        return null;
      }
      if (isAutoCloseable(element,pushed) && iter.hasNext()) {
        element=iter.next();
        if (isSameMethod(pushed,element)) {
          return null;
        }
      }
      return element;
    }
    throw new IllegalStateException();
  }
);
}
