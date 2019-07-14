public InstanceType getCanon() throws Exception {
  if (canon == null) {
    canon=new InstanceType(this,null,null,0);
  }
  return canon;
}
