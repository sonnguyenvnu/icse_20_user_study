/** 
 * Cleans up any dependencies that this handler might have.
 * @param context Context to use for unbinding if necessary.
 */
public void cleanup(Context context){
  if (isBoundToService())   unbindFromContext(context);
}
