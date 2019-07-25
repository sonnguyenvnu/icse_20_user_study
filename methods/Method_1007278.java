/** 
 * Returns the length of this <code>attribute_info</code> structure. The returned value is <code>attribute_length + 6</code>.
 */
@Override public int length(){
  return 18 + info.length + exceptions.size() * 8 + AttributeInfo.getLength(attributes);
}
