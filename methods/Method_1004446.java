public boolean register(final String name,final MetaManagementAction action){
  return actions.putIfAbsent(name,action) == null;
}
