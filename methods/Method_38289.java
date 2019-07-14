/** 
 * Separates from previous chunk by comma if is of the same type.
 */
protected void separateByCommaOrSpace(final StringBuilder out){
  if (isPreviousChunkOfSameType()) {
    out.append(',').append(' ');
  }
 else {
    appendMissingSpace(out);
  }
}
