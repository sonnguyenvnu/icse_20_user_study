/** 
 * Adds an attribute to this element. May only be called before an child element is added or this element has been closed. The attribute value is the decimal representation of the given long value.
 * @param name attribute name
 * @param value attribute value
 * @throws IOException in case of problems with the underlying output or if the element is already closed.
 */
public final void attr(final String name,final long value) throws IOException {
  attr(name,String.valueOf(value));
}
