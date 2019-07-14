/** 
 * Decodes and consumes the next frame from the FLAC stream into the given byte buffer. If any IO error occurs, resets the stream and input to the given  {@code retryPosition}.
 * @param output The byte buffer to hold the decoded frame.
 * @param retryPosition If any error happens, the input will be rewound to {@code retryPosition}.
 */
public void decodeSampleWithBacktrackPosition(ByteBuffer output,long retryPosition) throws InterruptedException, IOException, FlacFrameDecodeException {
  try {
    decodeSample(output);
  }
 catch (  IOException e) {
    if (retryPosition >= 0) {
      reset(retryPosition);
      if (extractorInput != null) {
        extractorInput.setRetryPosition(retryPosition,e);
      }
    }
    throw e;
  }
}
