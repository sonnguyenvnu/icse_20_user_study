/** 
 * Looks for keywords in the codec name to identify its nature ( {@link CodecType}).
 * @param codecInfo the currently inspected codec
 * @return type of the codec or null if it could bot be guessed
 */
@Nullable @TargetApi(Build.VERSION_CODES.JELLY_BEAN) private CodecType identifyCodecType(@NonNull MediaCodecInfo codecInfo){
  final String name=codecInfo.getName();
  for (  String token : AVC_TYPES) {
    if (name.contains(token)) {
      return CodecType.AVC;
    }
  }
  for (  String token : H263_TYPES) {
    if (name.contains(token)) {
      return CodecType.H263;
    }
  }
  for (  String token : MPEG4_TYPES) {
    if (name.contains(token)) {
      return CodecType.MPEG4;
    }
  }
  for (  String token : AAC_TYPES) {
    if (name.contains(token)) {
      return CodecType.AAC;
    }
  }
  return null;
}
