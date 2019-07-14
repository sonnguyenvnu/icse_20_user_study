@Override public <O>O getValueDirect(PropertyKey type){
  return (O)properties.get(type);
}
