/** 
 * Store the stream, and return the id. The stream is not closed.
 * @param in the stream
 * @return the id (potentially an empty array)
 * @throws IOException If an I/O error occurs
 */
@SuppressWarnings("resource") public byte[] put(InputStream in) throws IOException {
  ByteArrayOutputStream id=new ByteArrayOutputStream();
  int level=0;
  try {
    while (!put(id,in,level)) {
      if (id.size() > maxBlockSize / 2) {
        id=putIndirectId(id);
        level++;
      }
    }
  }
 catch (  IOException e) {
    remove(id.toByteArray());
    throw e;
  }
  if (id.size() > minBlockSize * 2) {
    id=putIndirectId(id);
  }
  return id.toByteArray();
}
