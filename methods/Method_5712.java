/** 
 * Returns an array containing  {@link Format#bitrate} values for given each format in order.
 * @param formats The format array to copy {@link Format#bitrate} values.
 * @param bitrates If not null, stores bitrate values in this array.
 * @return An array containing {@link Format#bitrate} values for given each format in order.
 */
public static int[] getFormatBitrates(Format[] formats,@Nullable int[] bitrates){
  int trackCount=formats.length;
  if (bitrates == null) {
    bitrates=new int[trackCount];
  }
  for (int i=0; i < trackCount; i++) {
    bitrates[i]=formats[i].bitrate;
  }
  return bitrates;
}
