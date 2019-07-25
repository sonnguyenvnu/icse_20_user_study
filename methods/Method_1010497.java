/** 
 * @deprecated use {@link #index(InputStream,Callback)} instead
 */
@Deprecated @ToRemove(version=0) public static void index(byte[] data,Callback newConsumer) throws IOException {
  index(new ByteArrayInputStream(data),newConsumer);
}
