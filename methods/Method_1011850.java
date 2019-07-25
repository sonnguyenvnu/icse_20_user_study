public T det(){
  if (myRows > 0 && myColumns > 0) {
    Object o=myCarrier[0][0];
    if (o instanceof Byte || o instanceof Short || o instanceof Long || o instanceof Integer) {
      Matrix<Double> d=new Matrix<Double>(this,soDouble);
      return (T)myOperations.cast(d._det());
    }
 else     if (o instanceof BigInteger) {
      Matrix<BigDecimal> bd=new Matrix<BigDecimal>(this,new BigDecimalScalarOperations(MathContext.DECIMAL128));
      return (T)myOperations.cast(bd._det());
    }
 else {
      return this._det();
    }
  }
 else   return null;
}
