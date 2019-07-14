/** 
 * Returns whether the specified codec is usable for decoding on the current device.
 * @param info The codec information.
 * @param name The name of the codec
 * @param secureDecodersExplicit Whether secure decoders were explicitly listed, if present.
 * @param requestedMimeType The originally requested MIME type, which may differ from the codeckey MIME type if the codec key is being considered as a fallback.
 * @return Whether the specified codec is usable for decoding on the current device.
 */
private static boolean isCodecUsableDecoder(android.media.MediaCodecInfo info,String name,boolean secureDecodersExplicit,String requestedMimeType){
  if (info.isEncoder() || (!secureDecodersExplicit && name.endsWith(".secure"))) {
    return false;
  }
  if (Util.SDK_INT < 21 && ("CIPAACDecoder".equals(name) || "CIPMP3Decoder".equals(name) || "CIPVorbisDecoder".equals(name) || "CIPAMRNBDecoder".equals(name) || "AACDecoder".equals(name) || "MP3Decoder".equals(name))) {
    return false;
  }
  if (Util.SDK_INT < 18 && "OMX.SEC.MP3.Decoder".equals(name)) {
    return false;
  }
  if ("OMX.SEC.mp3.dec".equals(name) && (Util.MODEL.startsWith("GT-I9152") || Util.MODEL.startsWith("GT-I9515") || Util.MODEL.startsWith("GT-P5220") || Util.MODEL.startsWith("GT-S7580") || Util.MODEL.startsWith("SM-G350") || Util.MODEL.startsWith("SM-G386") || Util.MODEL.startsWith("SM-T231") || Util.MODEL.startsWith("SM-T530") || Util.MODEL.startsWith("SCH-I535") || Util.MODEL.startsWith("SPH-L710"))) {
    return false;
  }
  if ("OMX.brcm.audio.mp3.decoder".equals(name) && (Util.MODEL.startsWith("GT-I9152") || Util.MODEL.startsWith("GT-S7580") || Util.MODEL.startsWith("SM-G350"))) {
    return false;
  }
  if (Util.SDK_INT < 18 && "OMX.MTK.AUDIO.DECODER.AAC".equals(name) && ("a70".equals(Util.DEVICE) || ("Xiaomi".equals(Util.MANUFACTURER) && Util.DEVICE.startsWith("HM")))) {
    return false;
  }
  if (Util.SDK_INT == 16 && "OMX.qcom.audio.decoder.mp3".equals(name) && ("dlxu".equals(Util.DEVICE) || "protou".equals(Util.DEVICE) || "ville".equals(Util.DEVICE) || "villeplus".equals(Util.DEVICE) || "villec2".equals(Util.DEVICE) || Util.DEVICE.startsWith("gee") || "C6602".equals(Util.DEVICE) || "C6603".equals(Util.DEVICE) || "C6606".equals(Util.DEVICE) || "C6616".equals(Util.DEVICE) || "L36h".equals(Util.DEVICE) || "SO-02E".equals(Util.DEVICE))) {
    return false;
  }
  if (Util.SDK_INT == 16 && "OMX.qcom.audio.decoder.aac".equals(name) && ("C1504".equals(Util.DEVICE) || "C1505".equals(Util.DEVICE) || "C1604".equals(Util.DEVICE) || "C1605".equals(Util.DEVICE))) {
    return false;
  }
  if (Util.SDK_INT < 24 && ("OMX.SEC.aac.dec".equals(name) || "OMX.Exynos.AAC.Decoder".equals(name)) && "samsung".equals(Util.MANUFACTURER) && (Util.DEVICE.startsWith("zeroflte") || Util.DEVICE.startsWith("zerolte") || Util.DEVICE.startsWith("zenlte") || "SC-05G".equals(Util.DEVICE) || "marinelteatt".equals(Util.DEVICE) || "404SC".equals(Util.DEVICE) || "SC-04G".equals(Util.DEVICE) || "SCV31".equals(Util.DEVICE))) {
    return false;
  }
  if (Util.SDK_INT <= 19 && "OMX.SEC.vp8.dec".equals(name) && "samsung".equals(Util.MANUFACTURER) && (Util.DEVICE.startsWith("d2") || Util.DEVICE.startsWith("serrano") || Util.DEVICE.startsWith("jflte") || Util.DEVICE.startsWith("santos") || Util.DEVICE.startsWith("t0"))) {
    return false;
  }
  if (Util.SDK_INT <= 19 && Util.DEVICE.startsWith("jflte") && "OMX.qcom.video.decoder.vp8".equals(name)) {
    return false;
  }
  if (MimeTypes.AUDIO_E_AC3_JOC.equals(requestedMimeType) && "OMX.MTK.AUDIO.DECODER.DSPAC3".equals(name)) {
    return false;
  }
  return true;
}
