/** 
 * {@inheritDoc}
 */
@Override public final void attribute(String name,int value) throws IOException {
  if (!this.isNude)   throw new IllegalStateException("Cannot write attribute: too late!");
  this.writer.write(' ');
  this.writer.write(name);
  this.writer.write('=');
  this.writer.write('"');
  this.writer.write(Integer.toString(value));
  this.writer.write('"');
}
