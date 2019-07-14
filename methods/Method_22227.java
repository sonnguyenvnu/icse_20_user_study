/** 
 * Builds a JSONObject describing the list of available codecs on the device with their capabilities (supported Color Formats, Codec Profiles and Levels).
 * @return The media codecs information
 */
@TargetApi(Build.VERSION_CODES.JELLY_BEAN) @NonNull private JSONObject collectMediaCodecList() throws JSONException {
  prepare();
  final MediaCodecInfo[] infos;
  if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
    final int codecCount=MediaCodecList.getCodecCount();
    infos=new MediaCodecInfo[codecCount];
    for (int codecIdx=0; codecIdx < codecCount; codecIdx++) {
      infos[codecIdx]=MediaCodecList.getCodecInfoAt(codecIdx);
    }
  }
 else {
    infos=new MediaCodecList(MediaCodecList.ALL_CODECS).getCodecInfos();
  }
  final JSONObject result=new JSONObject();
  for (int i=0; i < infos.length; i++) {
    final MediaCodecInfo codecInfo=infos[i];
    final JSONObject codec=new JSONObject();
    final String[] supportedTypes=codecInfo.getSupportedTypes();
    codec.put("name",codecInfo.getName()).put("isEncoder",codecInfo.isEncoder());
    final JSONObject supportedTypesJson=new JSONObject();
    for (    String type : supportedTypes) {
      supportedTypesJson.put(type,collectCapabilitiesForType(codecInfo,type));
    }
    codec.put("supportedTypes",supportedTypesJson);
    result.put(String.valueOf(i),codec);
  }
  return result;
}
