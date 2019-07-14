/** 
 * Append an octet number between 128 and 255 into mMessage. NOTE: A value between 0 and 127 should be appended by using appendShortInteger. This implementation doesn't check the validity of parameter, since it assumes that the values are validated in the GenericPdu setter methods.
 */
protected void appendOctet(int number){
  append(number);
}
