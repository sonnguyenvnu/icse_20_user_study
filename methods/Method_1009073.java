/** 
 * {@inheritDoc}
 */
@Override public void insert(DiffXEvent e) throws IOException {
  change(e,+1);
}
