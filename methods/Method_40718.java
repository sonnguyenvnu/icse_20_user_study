public InstanceType getCanon(){
  if (canon == null) {
    canon=new InstanceType(this);
  }
  return canon;
}
