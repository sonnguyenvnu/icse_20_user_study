/** 
 * Append date value into mMessage. This implementation doesn't check the validity of parameter, since it assumes that the values are validated in the GenericPdu setter methods.
 */
protected void appendDateValue(long date){
  appendLongInteger(date);
}
