/** 
 * Returns whether the headers match in those bits masked by  {@link #MPEG_AUDIO_HEADER_MASK}.
 */
private static boolean headersMatch(int headerA,long headerB){
  return (headerA & MPEG_AUDIO_HEADER_MASK) == (headerB & MPEG_AUDIO_HEADER_MASK);
}
