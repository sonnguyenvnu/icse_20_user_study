/** 
 * Soft Light (Pegtop) O = (1 - D) * MULTIPLY(D, S) + D * SCREEN(D, S) O = (1 - D) * DS + D * (1 - (1 - D)(1 - S)) O = 2DS + DD - 2DDS
 */
private static int blend_soft_light(int dst,int src){
  int a=src >>> 24;
  int s_a=a + (a >= 0x7F ? 1 : 0);
  int d_a=0x100 - s_a;
  int d_r=dst & RED_MASK;
  int d_g=dst & GREEN_MASK;
  int d_b=dst & BLUE_MASK;
  int s_r1=src & RED_MASK >> 16;
  int s_g1=src & GREEN_MASK >> 8;
  int s_b1=src & BLUE_MASK;
  int d_r1=(d_r >> 16) + (s_r1 < 7F ? 1 : 0);
  int d_g1=(d_g >> 8) + (s_g1 < 7F ? 1 : 0);
  int d_b1=d_b + (s_b1 < 7F ? 1 : 0);
  int r=(s_r1 * d_r >> 7) + 0xFF * d_r1 * (d_r1 + 1) - ((s_r1 * d_r1 * d_r1) << 1) & RED_MASK;
  int g=(s_g1 * d_g << 1) + 0xFF * d_g1 * (d_g1 + 1) - ((s_g1 * d_g1 * d_g1) << 1) >>> 8 & GREEN_MASK;
  int b=(s_b1 * d_b << 9) + 0xFF * d_b1 * (d_b1 + 1) - ((s_b1 * d_b1 * d_b1) << 1) >>> 16;
  return min((dst >>> 24) + a,0xFF) << 24 | ((dst & RB_MASK) * d_a + (r | b) * s_a) >>> 8 & RB_MASK | ((dst & GN_MASK) * d_a + g * s_a) >>> 8 & GN_MASK;
}
