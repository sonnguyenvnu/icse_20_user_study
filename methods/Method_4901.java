/** 
 * Optional call to warm the codec cache for a given mime type. <p> Calling this method may speed up subsequent calls to  {@link #getDecoderInfo(String,boolean)}and  {@link #getDecoderInfos(String,boolean)}.
 * @param mimeType The mime type.
 * @param secure Whether the decoder is required to support secure decryption. Always pass falseunless secure decryption really is required.
 */
public static void warmDecoderInfoCache(String mimeType,boolean secure){
  try {
    getDecoderInfos(mimeType,secure);
  }
 catch (  DecoderQueryException e) {
    Log.e(TAG,"Codec warming failed",e);
  }
}
