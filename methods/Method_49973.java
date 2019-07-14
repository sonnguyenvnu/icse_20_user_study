/** 
 * Append short integer value to mMessage. This implementation doesn't check the validity of parameter, since it assumes that the values are validated in the GenericPdu setter methods.
 */
protected void appendShortInteger(int value){
  append((value | 0x80) & 0xff);
}
