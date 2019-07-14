private static int getAc3SyncframeSize(int fscod,int frmsizecod){
  int halfFrmsizecod=frmsizecod / 2;
  if (fscod < 0 || fscod >= SAMPLE_RATE_BY_FSCOD.length || frmsizecod < 0 || halfFrmsizecod >= SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1.length) {
    return C.LENGTH_UNSET;
  }
  int sampleRate=SAMPLE_RATE_BY_FSCOD[fscod];
  if (sampleRate == 44100) {
    return 2 * (SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1[halfFrmsizecod] + (frmsizecod % 2));
  }
  int bitrate=BITRATE_BY_HALF_FRMSIZECOD[halfFrmsizecod];
  if (sampleRate == 32000) {
    return 6 * bitrate;
  }
 else {
    return 4 * bitrate;
  }
}
