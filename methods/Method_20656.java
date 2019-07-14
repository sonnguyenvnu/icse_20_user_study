/** 
 * Returns a currency string appropriate to the user's locale and preferred currency.
 * @param initialValue Value to convert, local to the project's currency.
 * @param project The project to use to look up currency information.
 */
public @NonNull String formatWithUserPreference(final double initialValue,final @NonNull Project project){
  return formatWithUserPreference(initialValue,project,RoundingMode.DOWN,0);
}
