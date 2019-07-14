static int FloatToS15_16(float flt){
  flt=flt * 65536f + 0.5f;
  if (flt <= -(65536f * 65536f)) {
    return Integer.MIN_VALUE;
  }
 else   if (flt >= (65536f * 65536f)) {
    return Integer.MAX_VALUE;
  }
 else {
    return (int)Math.floor(flt);
  }
}
