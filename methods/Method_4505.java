/** 
 * Returns the name of the FFmpeg decoder that could be used to decode the format, or  {@code null}if it's unsupported.
 */
static @Nullable String getCodecName(String mimeType,@C.PcmEncoding int encoding){
switch (mimeType) {
case MimeTypes.AUDIO_AAC:
    return "aac";
case MimeTypes.AUDIO_MPEG:
case MimeTypes.AUDIO_MPEG_L1:
case MimeTypes.AUDIO_MPEG_L2:
  return "mp3";
case MimeTypes.AUDIO_AC3:
return "ac3";
case MimeTypes.AUDIO_E_AC3:
case MimeTypes.AUDIO_E_AC3_JOC:
return "eac3";
case MimeTypes.AUDIO_TRUEHD:
return "truehd";
case MimeTypes.AUDIO_DTS:
case MimeTypes.AUDIO_DTS_HD:
return "dca";
case MimeTypes.AUDIO_VORBIS:
return "vorbis";
case MimeTypes.AUDIO_OPUS:
return "opus";
case MimeTypes.AUDIO_AMR_NB:
return "amrnb";
case MimeTypes.AUDIO_AMR_WB:
return "amrwb";
case MimeTypes.AUDIO_FLAC:
return "flac";
case MimeTypes.AUDIO_ALAC:
return "alac";
case MimeTypes.AUDIO_RAW:
if (encoding == C.ENCODING_PCM_MU_LAW) {
return "pcm_mulaw";
}
 else if (encoding == C.ENCODING_PCM_A_LAW) {
return "pcm_alaw";
}
 else {
return null;
}
default :
return null;
}
}
