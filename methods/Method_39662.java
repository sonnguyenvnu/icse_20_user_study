/** 
 * Appends the descriptor corresponding to this type to the given string buffer.
 * @param stringBuilder the string builder to which the descriptor must be appended.
 */
private void appendDescriptor(final StringBuilder stringBuilder){
  if (sort == OBJECT) {
    stringBuilder.append(valueBuffer,valueBegin - 1,valueEnd + 1);
  }
 else   if (sort == INTERNAL) {
    stringBuilder.append('L').append(valueBuffer,valueBegin,valueEnd).append(';');
  }
 else {
    stringBuilder.append(valueBuffer,valueBegin,valueEnd);
  }
}
