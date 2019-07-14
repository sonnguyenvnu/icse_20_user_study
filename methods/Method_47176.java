/** 
 * Eases the retrieval of primary colors ColorUsage. If the index is out of bounds, the first primary color is returned as default.
 * @param num The primary color index
 * @return The ColorUsage for the given primary color.
 */
public static @ColorInt int getPrimary(UserColorPreferences currentColors,int num){
  return num == 1 ? currentColors.primarySecondTab : currentColors.primaryFirstTab;
}
