public Q setBoolean(final String param,final Boolean value){
  if (value == null) {
    setNull(param,Types.BOOLEAN);
  }
 else {
    setBoolean(param,value.booleanValue());
  }
  return _this();
}
