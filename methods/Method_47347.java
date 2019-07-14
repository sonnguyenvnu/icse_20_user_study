/** 
 * Calls  {@link #doCopy(ReadableByteChannel,WritableByteChannel)}.
 * @see Channels#newChannel(InputStream)
 * @param bufferedInputStream source
 * @param outChannel target
 * @throws IOException
 */
@VisibleForTesting void copyFile(@NonNull BufferedInputStream bufferedInputStream,@NonNull FileChannel outChannel) throws IOException {
  doCopy(Channels.newChannel(bufferedInputStream),outChannel);
}
