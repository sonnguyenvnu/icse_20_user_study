/** 
 * Make a prettyprinted JSON text of this JSONObject. <p> Warning: This method assumes that the data structure is acyclical.
 * @param indentFactor The number of spaces to add to each level ofindentation.
 * @return a printable, displayable, portable, transmittablerepresentation of the object, beginning with <code>{</code>&nbsp;<small>(left brace)</small> and ending with <code>}</code>&nbsp;<small>(right brace)</small>.
 * @throws RuntimeException If the object contains an invalid number.
 */
public String format(int indentFactor){
  StringWriter w=new StringWriter();
synchronized (w.getBuffer()) {
    return this.writeInternal(w,indentFactor,0).toString();
  }
}
