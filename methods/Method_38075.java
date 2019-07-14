public Q setBoolean(final int index,final Boolean value){
  if (value == null) {
    setNull(index,Types.BOOLEAN);
  }
 else {
    setBoolean(index,value.booleanValue());
  }
  return _this();
}
