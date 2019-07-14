/** 
 * Append value length to mMessage. This implementation doesn't check the validity of parameter, since it assumes that the values are validated in the GenericPdu setter methods.
 */
protected void appendValueLength(long value){
  if (value < LENGTH_QUOTE) {
    appendShortLength((int)value);
    return;
  }
  append(LENGTH_QUOTE);
  appendUintvarInteger(value);
}
