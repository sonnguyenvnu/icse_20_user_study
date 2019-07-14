public void put(Authorize authorize){
  if (null == authorize || authorize.ignore()) {
    return;
  }
  permissions.addAll(Arrays.asList(authorize.permission()));
  actions.addAll(Arrays.asList(authorize.action()));
  roles.addAll(Arrays.asList(authorize.role()));
  user.addAll(Arrays.asList(authorize.user()));
  if (authorize.logical() != Logical.DEFAULT) {
    logical=authorize.logical();
  }
  message=authorize.message();
  phased=authorize.phased();
  put(authorize.dataAccess());
}
