/** 
 * Decodes and consumes the StreamInfo section from the FLAC stream. 
 */
public FlacStreamInfo decodeMetadata() throws IOException, InterruptedException {
  return flacDecodeMetadata(nativeDecoderContext);
}
