static public void registerOperation(ButterflyModule module,String name,Class<? extends AbstractOperation> klass){
  String key=module.getName() + "/" + name;
  s_opClassToName.put(klass,key);
  List<Class<? extends AbstractOperation>> classes=s_opNameToClass.get(key);
  if (classes == null) {
    classes=new LinkedList<Class<? extends AbstractOperation>>();
    s_opNameToClass.put(key,classes);
  }
  classes.add(klass);
}
