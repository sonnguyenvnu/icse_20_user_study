@SuppressWarnings("all") protected void addGlobalVariable(String var,Object val){
  engines.forEach(engine -> {
    try {
      engine.addGlobalVariable(Collections.singletonMap(var,val));
    }
 catch (    NullPointerException ignore) {
    }
  }
);
}
