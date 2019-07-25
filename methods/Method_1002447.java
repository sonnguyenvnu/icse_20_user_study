/** 
 * Write a newline as .pdl source. Typically used in conjunction with indent() and write() to emit an entire line of .pdl source.
 */
private void newline() throws IOException {
  _out.write(System.lineSeparator());
}
