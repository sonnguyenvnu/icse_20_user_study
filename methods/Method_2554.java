/** 
 * Read from a stream. The stream must implement the available() method.
 * @param stream
 * @throws java.io.IOException
 */
public void open(InputStream stream) throws IOException {
  int size=(int)(stream.available() / UNIT_SIZE);
  _array=new int[size];
  DataInputStream in=null;
  try {
    in=new DataInputStream(new BufferedInputStream(stream));
    for (int i=0; i < size; ++i) {
      _array[i]=in.readInt();
    }
  }
  finally {
    if (in != null) {
      in.close();
    }
  }
}
