/** 
 * Randomizes (but does not save) the colors used by the interface.
 * @return The {@link ColorPreference} object itself.
 */
public static UserColorPreferences randomize(Context c){
  @ColorRes int[] colorPos=RANDOM_COMBINATIONS[new Random().nextInt(RANDOM_COMBINATIONS.length)];
  return new UserColorPreferences(Utils.getColor(c,colorPos[0]),Utils.getColor(c,colorPos[0]),Utils.getColor(c,colorPos[1]),Utils.getColor(c,colorPos[2]));
}
