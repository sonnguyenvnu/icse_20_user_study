/** 
 * Calls  {@link #doCopy(ReadableByteChannel,WritableByteChannel)}.
 * @see Channels#newChannel(OutputStream)
 * @param inChannel source
 * @param bufferedOutputStream target
 * @throws IOException
 */
@VisibleForTesting void copyFile(@NonNull FileChannel inChannel,@NonNull BufferedOutputStream bufferedOutputStream) throws IOException {
  doCopy(inChannel,Channels.newChannel(bufferedOutputStream));
}
