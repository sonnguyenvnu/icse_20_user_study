private <T1,R>R execute(T1 val,Function1<T1,R> preferredFunction,Function1<T1,R> secondaryFunction){
  if (errorOnPreferredView) {
    log.warn("Routing request to secondary file-system view");
    return secondaryFunction.apply(val);
  }
 else {
    try {
      return preferredFunction.apply(val);
    }
 catch (    RuntimeException re) {
      log.error("Got error running preferred function. Trying secondary",re);
      errorOnPreferredView=true;
      return secondaryFunction.apply(val);
    }
  }
}
