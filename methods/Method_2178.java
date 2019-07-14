/** 
 * Transcodes Webp image given by input stream into png.
 */
@Override public void transcodeWebpToPng(InputStream inputStream,OutputStream outputStream) throws IOException {
  StaticWebpNativeLoader.ensure();
  nativeTranscodeWebpToPng(Preconditions.checkNotNull(inputStream),Preconditions.checkNotNull(outputStream));
}
