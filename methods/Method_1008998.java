/** 
 * Frees the underlying stream and property
 */
void free() throws IOException {
  _stream.free();
  _property.setStartBlock(POIFSConstants.END_OF_CHAIN);
}
