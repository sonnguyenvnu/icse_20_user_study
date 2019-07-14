/** 
 * Make a pretty-printed JSON text of this JSONArray. Warning: This method assumes that the data structure is acyclical.
 * @param indentFactor The number of spaces to add to each level ofindentation. Use -1 to specify no indentation and no newlines.
 * @return a printable, displayable, transmittablerepresentation of the object, beginning with <code>[</code>&nbsp;<small>(left bracket)</small> and ending with <code>]</code>&nbsp;<small>(right bracket)</small>.
 */
public String format(int indentFactor){
  StringWriter sw=new StringWriter();
synchronized (sw.getBuffer()) {
    return this.writeInternal(sw,indentFactor,0).toString();
  }
}
