/** 
 * Consumes char at current position. If char is different, throws the exception.
 */
protected void consume(final char c){
  if (input[ndx] != c) {
    syntaxError("Invalid char: expected " + c);
  }
  ndx++;
}
