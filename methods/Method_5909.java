/** 
 * Derives a mimeType from MP4 object type identifier, as defined in RFC 6381 and https://mp4ra.org/#/object_types.
 * @param objectType The objectType identifier to derive.
 * @return The mimeType, or null if it could not be derived.
 */
@Nullable public static String getMimeTypeFromMp4ObjectType(int objectType){
switch (objectType) {
case 0x20:
    return MimeTypes.VIDEO_MP4V;
case 0x21:
  return MimeTypes.VIDEO_H264;
case 0x23:
return MimeTypes.VIDEO_H265;
case 0x60:
case 0x61:
case 0x62:
case 0x63:
case 0x64:
case 0x65:
return MimeTypes.VIDEO_MPEG2;
case 0x6A:
return MimeTypes.VIDEO_MPEG;
case 0x69:
case 0x6B:
return MimeTypes.AUDIO_MPEG;
case 0xA3:
return MimeTypes.VIDEO_VC1;
case 0xB1:
return MimeTypes.VIDEO_VP9;
case 0x40:
case 0x66:
case 0x67:
case 0x68:
return MimeTypes.AUDIO_AAC;
case 0xA5:
return MimeTypes.AUDIO_AC3;
case 0xA6:
return MimeTypes.AUDIO_E_AC3;
case 0xA9:
case 0xAC:
return MimeTypes.AUDIO_DTS;
case 0xAA:
case 0xAB:
return MimeTypes.AUDIO_DTS_HD;
case 0xAD:
return MimeTypes.AUDIO_OPUS;
default :
return null;
}
}
