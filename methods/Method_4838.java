/** 
 * Peeks and returns a  {@code WavHeader}.
 * @param input Input stream to peek the WAV header from.
 * @throws ParserException If the input file is an incorrect RIFF WAV.
 * @throws IOException If peeking from the input fails.
 * @throws InterruptedException If interrupted while peeking from input.
 * @return A new {@code WavHeader} peeked from {@code input}, or null if the input is not a supported WAV format.
 */
public static WavHeader peek(ExtractorInput input) throws IOException, InterruptedException {
  Assertions.checkNotNull(input);
  ParsableByteArray scratch=new ParsableByteArray(16);
  ChunkHeader chunkHeader=ChunkHeader.peek(input,scratch);
  if (chunkHeader.id != WavUtil.RIFF_FOURCC) {
    return null;
  }
  input.peekFully(scratch.data,0,4);
  scratch.setPosition(0);
  int riffFormat=scratch.readInt();
  if (riffFormat != WavUtil.WAVE_FOURCC) {
    Log.e(TAG,"Unsupported RIFF format: " + riffFormat);
    return null;
  }
  chunkHeader=ChunkHeader.peek(input,scratch);
  while (chunkHeader.id != WavUtil.FMT_FOURCC) {
    input.advancePeekPosition((int)chunkHeader.size);
    chunkHeader=ChunkHeader.peek(input,scratch);
  }
  Assertions.checkState(chunkHeader.size >= 16);
  input.peekFully(scratch.data,0,16);
  scratch.setPosition(0);
  int type=scratch.readLittleEndianUnsignedShort();
  int numChannels=scratch.readLittleEndianUnsignedShort();
  int sampleRateHz=scratch.readLittleEndianUnsignedIntToInt();
  int averageBytesPerSecond=scratch.readLittleEndianUnsignedIntToInt();
  int blockAlignment=scratch.readLittleEndianUnsignedShort();
  int bitsPerSample=scratch.readLittleEndianUnsignedShort();
  int expectedBlockAlignment=numChannels * bitsPerSample / 8;
  if (blockAlignment != expectedBlockAlignment) {
    throw new ParserException("Expected block alignment: " + expectedBlockAlignment + "; got: " + blockAlignment);
  }
  @C.PcmEncoding int encoding=WavUtil.getEncodingForType(type,bitsPerSample);
  if (encoding == C.ENCODING_INVALID) {
    Log.e(TAG,"Unsupported WAV format: " + bitsPerSample + " bit/sample, type " + type);
    return null;
  }
  input.advancePeekPosition((int)chunkHeader.size - 16);
  return new WavHeader(numChannels,sampleRateHz,averageBytesPerSecond,blockAlignment,bitsPerSample,encoding);
}
