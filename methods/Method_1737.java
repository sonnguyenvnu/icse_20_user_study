/** 
 * Creates a writer callback that copies all the content read from an  {@link InputStream} intothe target stream. <p>This writer can be used only once.
 * @param is the source
 * @return the writer callback
 */
public static WriterCallback from(final InputStream is){
  return new WriterCallback(){
    @Override public void write(    OutputStream os) throws IOException {
      ByteStreams.copy(is,os);
    }
  }
;
}
