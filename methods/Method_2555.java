/** 
 * Saves the trie data into a stream.
 * @param stream
 * @throws java.io.IOException
 */
public void save(OutputStream stream) throws IOException {
  DataOutputStream out=null;
  try {
    out=new DataOutputStream(new BufferedOutputStream(stream));
    for (int i=0; i < _array.length; ++i) {
      out.writeInt(_array[i]);
    }
  }
  finally {
    if (out != null) {
      out.close();
    }
  }
}
