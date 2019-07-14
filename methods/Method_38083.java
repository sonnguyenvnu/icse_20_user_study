public Q setByte(final int index,final Number value){
  if (value == null) {
    setNull(index,Types.SMALLINT);
  }
 else {
    setByte(index,value.byteValue());
  }
  return _this();
}
