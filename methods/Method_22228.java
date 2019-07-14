/** 
 * Retrieve capabilities (ColorFormats and CodecProfileLevels) for a specific codec type.
 * @param codecInfo the currently inspected codec
 * @param type      supported type to collect
 * @return the color formats and codec profile levels available for a specific codec type.
 */
@NonNull @TargetApi(Build.VERSION_CODES.JELLY_BEAN) private JSONObject collectCapabilitiesForType(@NonNull final MediaCodecInfo codecInfo,@NonNull String type) throws JSONException {
  final JSONObject result=new JSONObject();
  final MediaCodecInfo.CodecCapabilities codecCapabilities=codecInfo.getCapabilitiesForType(type);
  final int[] colorFormats=codecCapabilities.colorFormats;
  if (colorFormats.length > 0) {
    final JSONArray colorFormatsJson=new JSONArray();
    for (    int colorFormat : colorFormats) {
      colorFormatsJson.put(mColorFormatValues.get(colorFormat));
    }
    result.put("colorFormats",colorFormatsJson);
  }
  final CodecType codecType=identifyCodecType(codecInfo);
  final MediaCodecInfo.CodecProfileLevel[] codecProfileLevels=codecCapabilities.profileLevels;
  if (codecProfileLevels.length > 0) {
    final JSONArray profileLevels=new JSONArray();
    for (    MediaCodecInfo.CodecProfileLevel codecProfileLevel : codecProfileLevels) {
      final int profileValue=codecProfileLevel.profile;
      final int levelValue=codecProfileLevel.level;
      if (codecType == null) {
        profileLevels.put(profileValue + '-' + levelValue);
        break;
      }
switch (codecType) {
case AVC:
        profileLevels.put(profileValue + mAVCProfileValues.get(profileValue) + '-' + mAVCLevelValues.get(levelValue));
      break;
case H263:
    profileLevels.put(mH263ProfileValues.get(profileValue) + '-' + mH263LevelValues.get(levelValue));
  break;
case MPEG4:
profileLevels.put(mMPEG4ProfileValues.get(profileValue) + '-' + mMPEG4LevelValues.get(levelValue));
break;
case AAC:
profileLevels.put(mAACProfileValues.get(profileValue));
break;
default :
break;
}
}
result.put("profileLevels",profileLevels);
}
return result;
}
