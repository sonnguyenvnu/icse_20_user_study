public static short[] decode(short version){
  short major=(short)((version & MAJOR_MASK) >> MAJOR_SHIFT);
  short minor=(short)((version & MINOR_MASK) >> MINOR_SHIFT);
  short micro=(short)(version & MICRO_MASK);
  return new short[]{major,minor,micro};
}
