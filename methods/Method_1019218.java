private <R>R execute(Function0<R> preferredFunction,Function0<R> secondaryFunction){
  if (errorOnPreferredView) {
    log.warn("Routing request to secondary file-system view");
    return secondaryFunction.apply();
  }
 else {
    try {
      return preferredFunction.apply();
    }
 catch (    RuntimeException re) {
      log.error("Got error running preferred function. Trying secondary",re);
      errorOnPreferredView=true;
      return secondaryFunction.apply();
    }
  }
}
