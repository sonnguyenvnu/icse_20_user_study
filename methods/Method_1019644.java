/** 
 * Write the content of this element in the given writer.
 */
public void save(Writer writer) throws IOException {
  getStartTagElement().save(writer);
  getEndTagElement().save(writer);
}
