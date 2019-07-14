/** 
 * Screen O = 1 - (1 - D)(1 - S) O = D + S - DS
 */
private static int blend_screen(int dst,int src){
  int a=src >>> 24;
  int s_a=a + (a >= 0x7F ? 1 : 0);
  int d_a=0x100 - s_a;
  int d_rb=dst & RB_MASK;
  int d_gn=dst & GN_MASK;
  int s_gn=src & GN_MASK;
  int f_r=(dst & RED_MASK) >> 16;
  int f_b=(dst & BLUE_MASK);
  int rb_sub=((src & RED_MASK) * (f_r + 1) | (src & BLUE_MASK) * (f_b + 1)) >>> 8 & RB_MASK;
  int gn_sub=s_gn * (d_gn + 0x100) >>> 16 & GN_MASK;
  return min((dst >>> 24) + a,0xFF) << 24 | (d_rb * d_a + (d_rb + (src & RB_MASK) - rb_sub) * s_a) >>> 8 & RB_MASK | (d_gn * d_a + (d_gn + s_gn - gn_sub) * s_a) >>> 8 & GN_MASK;
}
