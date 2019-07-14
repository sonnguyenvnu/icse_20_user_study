private static int getMaximumEncodedRateBytesPerSecond(@C.Encoding int encoding){
switch (encoding) {
case C.ENCODING_AC3:
    return 640 * 1000 / 8;
case C.ENCODING_E_AC3:
  return 6144 * 1000 / 8;
case C.ENCODING_DTS:
return 1536 * 1000 / 8;
case C.ENCODING_DTS_HD:
return 18000 * 1000 / 8;
case C.ENCODING_DOLBY_TRUEHD:
return 24500 * 1000 / 8;
case C.ENCODING_INVALID:
case C.ENCODING_PCM_16BIT:
case C.ENCODING_PCM_24BIT:
case C.ENCODING_PCM_32BIT:
case C.ENCODING_PCM_8BIT:
case C.ENCODING_PCM_A_LAW:
case C.ENCODING_PCM_FLOAT:
case C.ENCODING_PCM_MU_LAW:
case Format.NO_VALUE:
default :
throw new IllegalArgumentException();
}
}
