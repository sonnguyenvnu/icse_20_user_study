/** 
 * Returns the descriptor corresponding to this type.
 * @return the descriptor corresponding to this type.
 */
public String getDescriptor(){
  if (sort == OBJECT) {
    return valueBuffer.substring(valueBegin - 1,valueEnd + 1);
  }
 else   if (sort == INTERNAL) {
    return 'L' + valueBuffer.substring(valueBegin,valueEnd) + ';';
  }
 else {
    return valueBuffer.substring(valueBegin,valueEnd);
  }
}
