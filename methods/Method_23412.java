/** 
 * Darkest O = MIN(D, S)
 */
private static int blend_darkest(int dst,int src){
  int a=src >>> 24;
  int s_a=a + (a >= 0x7F ? 1 : 0);
  int d_a=0x100 - s_a;
  int rb=min(src & RED_MASK,dst & RED_MASK) | min(src & BLUE_MASK,dst & BLUE_MASK);
  int gn=min(src & GREEN_MASK,dst & GREEN_MASK);
  return min((dst >>> 24) + a,0xFF) << 24 | ((dst & RB_MASK) * d_a + rb * s_a) >>> 8 & RB_MASK | ((dst & GN_MASK) * d_a + gn * s_a) >>> 8 & GN_MASK;
}
