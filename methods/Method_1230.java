/** 
 * Sets the actual image resource to the given resource ID. Similar to  {@link #setImageResource(int)}, this sets the displayed image to the given resource. However,  {@link #setImageResource(int)} bypasses all Drawee functionality and makes the viewact as a normal  {@link android.widget.ImageView}, whereas this method keeps all of the Drawee functionality, including the  {@link com.facebook.drawee.interfaces.DraweeHierarchy}.
 * @param resourceId the resource ID to use.
 * @param callerContext caller context
 */
public void setActualImageResource(@DrawableRes int resourceId,@Nullable Object callerContext){
  setImageURI(UriUtil.getUriForResourceId(resourceId),callerContext);
}
