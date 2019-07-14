public Q setByte(final String param,final Number value){
  if (value == null) {
    setNull(param,Types.SMALLINT);
  }
 else {
    setByte(param,value.byteValue());
  }
  return _this();
}
