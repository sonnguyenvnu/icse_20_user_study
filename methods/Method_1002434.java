/** 
 * Write contents of this message into the provided  {@link Formatter} usingthe the field separator provided by  {@link #getFieldSeparator()}.
 * @param formatter provides the {@link Formatter} to write this {@link Message} to.
 * @return the provided {@link Formatter}.
 */
public Formatter format(Formatter formatter){
  return format(formatter,getFieldSeparator());
}
