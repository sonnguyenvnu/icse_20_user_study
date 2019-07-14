public Q setBigInteger(final String param,final BigInteger value){
  if (value == null) {
    setNull(param,Types.NUMERIC);
  }
 else {
    setLong(param,value.longValue());
  }
  return _this();
}
