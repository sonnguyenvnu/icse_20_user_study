/** 
 * Close this GifSequenceWriter object. This does not close the underlying stream, just finishes off the GIF.
 */
@Override public void close() throws IOException {
  gifWriter.endWriteSequence();
}
