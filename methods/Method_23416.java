/** 
 * Hard Light O = OVERLAY(S, D) O = 2 * MULTIPLY(D, S) = 2DS                   for S < 0.5 O = 2 * SCREEN(D, S) - 1 = 2(S + D - DS) - 1   otherwise
 */
private static int blend_hard_light(int dst,int src){
  int a=src >>> 24;
  int s_a=a + (a >= 0x7F ? 1 : 0);
  int d_a=0x100 - s_a;
  int d_r=dst & RED_MASK;
  int d_g=dst & GREEN_MASK;
  int d_b=dst & BLUE_MASK;
  int s_r=src & RED_MASK;
  int s_g=src & GREEN_MASK;
  int s_b=src & BLUE_MASK;
  int r=(s_r < 0x800000) ? s_r * ((d_r >>> 16) + 1) >>> 7 : 0xFF0000 - ((0x100 - (d_r >>> 16)) * (RED_MASK - s_r) >>> 7);
  int g=(s_g < 0x8000) ? s_g * (d_g + 0x100) >>> 15 : (0xFF00 - ((0x10000 - d_g) * (GREEN_MASK - s_g) >>> 15));
  int b=(s_b < 0x80) ? s_b * (d_b + 1) >>> 7 : (0xFF00 - ((0x100 - d_b) * (BLUE_MASK - s_b) << 1)) >>> 8;
  return min((dst >>> 24) + a,0xFF) << 24 | ((dst & RB_MASK) * d_a + ((r | b) & RB_MASK) * s_a) >>> 8 & RB_MASK | ((dst & GN_MASK) * d_a + (g & GN_MASK) * s_a) >>> 8 & GN_MASK;
}
