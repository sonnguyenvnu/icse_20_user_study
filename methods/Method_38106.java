public Q setBigInteger(final int index,final BigInteger value){
  if (value == null) {
    setNull(index,Types.NUMERIC);
  }
 else {
    setLong(index,value.longValue());
  }
  return _this();
}
