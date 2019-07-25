@Override public Action build(){
  return new ActionImpl(getId(),getTypeUID(),configuration,label,description,inputs);
}
