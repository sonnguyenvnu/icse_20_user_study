/** 
 * Calculate the red value for different level.
 * @param baseR Red value of base color.
 * @param level Level.
 * @return The red value for the level of the base color.
 */
private static int calculateR(int baseR,int level){
switch (level) {
case 0:
    return 238;
case 1:
  return baseR;
case 2:
return (int)(baseR * (9 + 46 + 15) / (37f + 9 + 46 + 15));
case 3:
return (int)(baseR * (46 + 15) / (37f + 9 + 46 + 15));
case 4:
return (int)(baseR * (15) / (37f + 9 + 46 + 15));
default :
return 238;
}
}
