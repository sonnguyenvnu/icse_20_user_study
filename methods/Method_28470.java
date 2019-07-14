/** 
 * Calculate the blue value for different level.
 * @param baseB Blue value of base color.
 * @param level Level.
 * @return The blue value for the level of the base color.
 */
private static int calculateB(int baseB,int level){
switch (level) {
case 0:
    return 238;
case 1:
  return baseB;
case 2:
return (int)(baseB * (37 + 29 + 35) / (32f + 37 + 29 + 35));
case 3:
return (int)(baseB * (29 + 35) / (32f + 37 + 29 + 35));
case 4:
return (int)(baseB * (35) / (32f + 37 + 29 + 35));
default :
return 238;
}
}
