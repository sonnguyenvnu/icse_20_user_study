/** 
 * Add O = MIN(D + S, 1)
 */
private static int blend_add_pin(int dst,int src){
  int a=src >>> 24;
  int s_a=a + (a >= 0x7F ? 1 : 0);
  int rb=(dst & RB_MASK) + ((src & RB_MASK) * s_a >>> 8 & RB_MASK);
  int gn=(dst & GN_MASK) + ((src & GN_MASK) * s_a >>> 8);
  return min((dst >>> 24) + a,0xFF) << 24 | min(rb & 0xFFFF0000,RED_MASK) | min(gn & 0x00FFFF00,GREEN_MASK) | min(rb & 0x0000FFFF,BLUE_MASK);
}
