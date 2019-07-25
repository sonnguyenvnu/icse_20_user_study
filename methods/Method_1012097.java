public boolean seen(DepLink depLink){
  return SetSequence.fromSet(mySeen).contains(depLink.getRoleModuleKey());
}
