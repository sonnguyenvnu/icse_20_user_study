@Override public void call(Object obj) throws InvocationTargetException {
  LOG.debug("calling action {} on instance {}",description,obj);
  if (mh != null) {
    try {
      mh.invoke(obj);
    }
 catch (    Throwable throwable) {
      if (throwable instanceof RuntimeException) {
        throw (RuntimeException)throwable;
      }
 else       if (throwable instanceof Error) {
        throw (Error)throwable;
      }
      throw new InvocationTargetException(throwable,"invoke-dynamic");
    }
  }
 else {
    try {
      method.invoke(obj);
    }
 catch (    InvocationTargetException ite) {
      Throwable cause=ite.getCause();
      if (cause instanceof RuntimeException) {
        throw (RuntimeException)cause;
      }
 else       if (cause instanceof Error) {
        throw (Error)cause;
      }
      throw ite;
    }
catch (    Throwable e) {
      throw new RuntimeException("unexpected exception in method invocation",e);
    }
  }
}
