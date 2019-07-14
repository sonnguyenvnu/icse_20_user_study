/** 
 * Returns whether the requirements are met.
 * @param context Any context.
 * @return Whether the requirements are met.
 */
public boolean checkRequirements(Context context){
  return getNotMetRequirements(context) == 0;
}
