/** 
 * Calculate the green value for different level.
 * @param baseG Green value of base color.
 * @param level Level.
 * @return The green value for the level of the base color.
 */
private static int calculateG(int baseG,int level){
switch (level) {
case 0:
    return 238;
case 1:
  return baseG;
case 2:
return (int)(baseG * (35 + 59 + 104) / (32f + 35 + 59 + 104));
case 3:
return (int)(baseG * (59 + 104) / (32f + 35 + 59 + 104));
case 4:
return (int)(baseG * (104) / (32f + 35 + 59 + 104));
default :
return 238;
}
}
