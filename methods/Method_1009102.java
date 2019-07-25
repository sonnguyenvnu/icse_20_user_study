/** 
 * Closes the writer. <p>This method only checks that it is possible to close the writer.
 * @throws IOException If thrown by the wrapped writer.
 * @throws UnclosedElementException If an element has been left open.
 */
@Override public void close() throws IOException, UnclosedElementException {
  Element open=peekElement();
  if (open != ROOT)   throw new UnclosedElementException(open.name);
}
