/** 
 * @param showOtherDates int flag for show other dates
 * @return true if the decorated disabled flag is set
 */
public static boolean showDecoratedDisabled(@ShowOtherDates int showOtherDates){
  return (showOtherDates & SHOW_DECORATED_DISABLED) != 0;
}
