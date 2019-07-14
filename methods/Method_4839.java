/** 
 * Skips to the data in the given WAV input stream. After calling, the input stream's position will point to the start of sample data in the WAV, and the data bounds of the provided  {@link WavHeader} will have been set.<p>If an exception is thrown, the input position will be left pointing to a chunk header and the bounds of the provided  {@link WavHeader} will not have been set.
 * @param input Input stream to skip to the data chunk in. Its peek position must be pointing to avalid chunk header.
 * @param wavHeader WAV header to populate with data bounds.
 * @throws ParserException If an error occurs parsing chunks.
 * @throws IOException If reading from the input fails.
 * @throws InterruptedException If interrupted while reading from input.
 */
public static void skipToData(ExtractorInput input,WavHeader wavHeader) throws IOException, InterruptedException {
  Assertions.checkNotNull(input);
  Assertions.checkNotNull(wavHeader);
  input.resetPeekPosition();
  ParsableByteArray scratch=new ParsableByteArray(ChunkHeader.SIZE_IN_BYTES);
  ChunkHeader chunkHeader=ChunkHeader.peek(input,scratch);
  while (chunkHeader.id != Util.getIntegerCodeForString("data")) {
    Log.w(TAG,"Ignoring unknown WAV chunk: " + chunkHeader.id);
    long bytesToSkip=ChunkHeader.SIZE_IN_BYTES + chunkHeader.size;
    if (chunkHeader.id == Util.getIntegerCodeForString("RIFF")) {
      bytesToSkip=ChunkHeader.SIZE_IN_BYTES + 4;
    }
    if (bytesToSkip > Integer.MAX_VALUE) {
      throw new ParserException("Chunk is too large (~2GB+) to skip; id: " + chunkHeader.id);
    }
    input.skipFully((int)bytesToSkip);
    chunkHeader=ChunkHeader.peek(input,scratch);
  }
  input.skipFully(ChunkHeader.SIZE_IN_BYTES);
  wavHeader.setDataBounds(input.getPosition(),chunkHeader.size);
}
