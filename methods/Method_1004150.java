/** 
 * Adds the given text as a child to this node. The text will be quoted. May only be called before this element has been closed.
 * @param text text to add
 * @throws IOException in case of problems with the underlying output or if the element is already closed.
 */
public final void text(final String text) throws IOException {
  if (closed) {
    throw new IOException(format("Element %s already closed.",name));
  }
  finishOpenTag();
  if (lastchild != null) {
    lastchild.close();
  }
  quote(text);
}
