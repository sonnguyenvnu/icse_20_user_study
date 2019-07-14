public Object[] getArgumentStrings(boolean nullsOk) throws CouldNotGenerateValueException {
  Object[] values=new Object[assigned.size()];
  for (int i=0; i < values.length; i++) {
    values[i]=assigned.get(i).getDescription();
  }
  return values;
}
