public Q setShort(final String param,final Number value){
  if (value == null) {
    setNull(param,Types.SMALLINT);
  }
 else {
    setShort(param,value.shortValue());
  }
  return _this();
}
