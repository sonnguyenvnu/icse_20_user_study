/** 
 * Overlay O = 2 * MULTIPLY(D, S) = 2DS                   for D < 0.5 O = 2 * SCREEN(D, S) - 1 = 2(S + D - DS) - 1   otherwise
 */
private static int blend_overlay(int dst,int src){
  int a=src >>> 24;
  int s_a=a + (a >= 0x7F ? 1 : 0);
  int d_a=0x100 - s_a;
  int d_r=dst & RED_MASK;
  int d_g=dst & GREEN_MASK;
  int d_b=dst & BLUE_MASK;
  int s_r=src & RED_MASK;
  int s_g=src & GREEN_MASK;
  int s_b=src & BLUE_MASK;
  int r=(d_r < 0x800000) ? d_r * ((s_r >>> 16) + 1) >>> 7 : 0xFF0000 - ((0x100 - (s_r >>> 16)) * (RED_MASK - d_r) >>> 7);
  int g=(d_g < 0x8000) ? d_g * (s_g + 0x100) >>> 15 : (0xFF00 - ((0x10000 - s_g) * (GREEN_MASK - d_g) >>> 15));
  int b=(d_b < 0x80) ? d_b * (s_b + 1) >>> 7 : (0xFF00 - ((0x100 - s_b) * (BLUE_MASK - d_b) << 1)) >>> 8;
  return min((dst >>> 24) + a,0xFF) << 24 | ((dst & RB_MASK) * d_a + ((r | b) & RB_MASK) * s_a) >>> 8 & RB_MASK | ((dst & GN_MASK) * d_a + (g & GN_MASK) * s_a) >>> 8 & GN_MASK;
}
