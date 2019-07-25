/** 
 * {@inheritDoc}
 */
@Override public void delete(DiffXEvent e) throws IOException {
  change(e,-1);
}
