/** 
 * Casts a specified value to this data type taking precision and scale into account.
 * @param value value to cast
 * @param mode database mode
 * @param convertPrecision if  {@code true}, value is truncated to the precision of data type when possible, if  {@code false} an exception in thrownfor too large values
 * @param column column, or null
 * @return casted value
 * @throws DbException if value cannot be casted to this data type
 */
public Value cast(Value value,Mode mode,boolean convertPrecision,Object column){
  value=value.convertTo(this,mode,column).convertScale(mode.convertOnlyToSmallerScale,scale);
  if (convertPrecision) {
    value=value.convertPrecision(precision);
  }
 else   if (!value.checkPrecision(precision)) {
    throw getValueTooLongException(value,column);
  }
  return value;
}
