/** 
 * Get TextString value by header field.
 * @param field the field
 * @return the TextString value of the pdu headerwith specified header field
 */
protected byte[] getTextString(int field){
  return (byte[])mHeaderMap.get(field);
}
