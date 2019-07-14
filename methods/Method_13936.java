@Override public String visit(EntityIdValue value){
  if (ReconEntityIdValue.class.isInstance(value) && ((ReconEntityIdValue)value).isNew()) {
    return "LAST";
  }
  return value.getId();
}
