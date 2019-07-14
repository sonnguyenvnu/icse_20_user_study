/** 
 * This method returns the reference of the View with the given Id in the layout of the Activity passed as parameter
 * @param act    The Activity that is using the layout with the given View
 * @param viewId The id of the View we want to get a reference
 * @return The View with the given id and type
 */
public static <T extends View>T findViewById(Activity act,int viewId){
  View containerView=act.getWindow().getDecorView();
  return findViewById(containerView,viewId);
}
