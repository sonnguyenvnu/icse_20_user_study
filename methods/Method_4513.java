/** 
 * Returns whether the decoder has read to the end of the input. 
 */
public boolean isDecoderAtEndOfInput(){
  return flacIsDecoderAtEndOfStream(nativeDecoderContext);
}
