static public void registerReconConfig(ButterflyModule module,String name,Class<? extends ReconConfig> klass){
  String key=module.getName() + "/" + name;
  s_opClassToName.put(klass,key);
  List<Class<? extends ReconConfig>> classes=s_opNameToClass.get(key);
  if (classes == null) {
    classes=new LinkedList<Class<? extends ReconConfig>>();
    s_opNameToClass.put(key,classes);
  }
  classes.add(klass);
}
