/** 
 * Writes the properties out into the given low-level stream
 */
public void write(NPOIFSStream stream) throws IOException {
  OutputStream os=stream.getOutputStream();
  for (  Property property : _properties) {
    if (property != null) {
      property.writeData(os);
    }
  }
  os.close();
  if (getStartBlock() != stream.getStartBlock()) {
    setStartBlock(stream.getStartBlock());
  }
}
