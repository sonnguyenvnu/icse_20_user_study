private <T1,T2,R>R execute(T1 val,T2 val2,Function2<T1,T2,R> preferredFunction,Function2<T1,T2,R> secondaryFunction){
  if (errorOnPreferredView) {
    log.warn("Routing request to secondary file-system view");
    return secondaryFunction.apply(val,val2);
  }
 else {
    try {
      return preferredFunction.apply(val,val2);
    }
 catch (    RuntimeException re) {
      log.error("Got error running preferred function. Trying secondary",re);
      errorOnPreferredView=true;
      return secondaryFunction.apply(val,val2);
    }
  }
}
