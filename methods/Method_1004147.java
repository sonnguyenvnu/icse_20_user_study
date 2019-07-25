/** 
 * Adds an attribute to this element. May only be called before an child element is added or this element has been closed. The attribute value will be quoted. If the value is <code>null</code> the attribute will not be added.
 * @param name attribute name
 * @param value attribute value or <code>null</code>
 * @throws IOException in case of problems with the underlying output or if the element is already closed.
 */
public final void attr(final String name,final String value) throws IOException {
  if (value == null) {
    return;
  }
  if (closed || openTagDone) {
    throw new IOException(format("Element %s already closed.",this.name));
  }
  writer.write(' ');
  writer.write(name);
  writer.write('=');
  writer.write('"');
  quote(value);
  writer.write('"');
}
