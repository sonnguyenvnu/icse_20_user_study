/** 
 * Peeks from the beginning of the input to see if  {@link #FLAC_SIGNATURE} is present.
 * @return Whether the input begins with {@link #FLAC_SIGNATURE}.
 */
private boolean peekFlacSignature(ExtractorInput input) throws IOException, InterruptedException {
  byte[] header=new byte[FLAC_SIGNATURE.length];
  input.peekFully(header,0,FLAC_SIGNATURE.length);
  return Arrays.equals(header,FLAC_SIGNATURE);
}
