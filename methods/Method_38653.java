@Override public boolean serialize(final JsonContext jsonContext,final LocalTime value){
  jsonContext.writeNumber(value.toSecondOfDay());
  return true;
}
