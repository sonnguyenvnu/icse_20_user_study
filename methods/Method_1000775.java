public Ioc create(NutConfig config,String[] args){
  try {
    for (int i=0; i < args.length; i++) {
      if (args[i].contains("${main}"))       args[i]=args[i].replace("${main}",config.getMainModule().getPackage().getName());
    }
    return new NutIoc(new ComboIocLoader(args),new ScopeContext("app"),"app");
  }
 catch (  ClassNotFoundException e) {
    throw Lang.wrapThrow(e);
  }
}
