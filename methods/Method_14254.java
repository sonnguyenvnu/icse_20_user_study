static public Properties createBindings(Project project){
  Properties bindings=new Properties();
  bindings.put("true",true);
  bindings.put("false",false);
  bindings.put("PI",Math.PI);
  bindings.put("project",project);
  for (  Binder binder : s_binders) {
    binder.initializeBindings(bindings,project);
  }
  return bindings;
}
