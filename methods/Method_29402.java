private boolean isMaster(InstanceStats instanceStats){
  return instanceStats.getRole() == 1 ? true : false;
}
