/** 
 * 1???
 * @param unit
 * @return
 */
private static int popCount(int unit){
  unit=((unit & 0xAAAAAAAA) >>> 1) + (unit & 0x55555555);
  unit=((unit & 0xCCCCCCCC) >>> 2) + (unit & 0x33333333);
  unit=((unit >>> 4) + unit) & 0x0F0F0F0F;
  unit+=unit >>> 8;
  unit+=unit >>> 16;
  return unit & 0xFF;
}
