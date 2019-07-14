/** 
 * Creates char sub-sequence from the input.
 */
protected final CharSequence charSequence(final int from,final int to){
  if (from == to) {
    return CharArraySequence.EMPTY;
  }
  return CharArraySequence.of(input,from,to - from);
}
