/** 
 * Seeks relative to the beginning of the QuickTime stream. <p> Usually this equal to seeking in the underlying ImageOutputStream, but can be different if the underlying stream already contained data.
 */
private void seekRelative(long newPosition) throws IOException {
  out.seek(newPosition + streamOffset);
}
