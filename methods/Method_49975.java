/** 
 * Append a short length into mMessage. This implementation doesn't check the validity of parameter, since it assumes that the values are validated in the GenericPdu setter methods.
 */
protected void appendShortLength(int value){
  append(value);
}
