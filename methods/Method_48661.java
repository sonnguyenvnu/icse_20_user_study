private E getValue(long ordinal){
  E[] values=datatype.getEnumConstants();
  Preconditions.checkArgument(ordinal >= 0 && ordinal < values.length,"Invalid ordinal number (max %s): %s",values.length,ordinal);
  return values[(int)ordinal];
}
