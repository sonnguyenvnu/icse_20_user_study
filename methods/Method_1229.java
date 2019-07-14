/** 
 * Displays an image given by the uri.
 * @param uri uri of the image
 * @param callerContext caller context
 */
public void setImageURI(Uri uri,@Nullable Object callerContext){
  DraweeController controller=mControllerBuilder.setCallerContext(callerContext).setUri(uri).setOldController(getController()).build();
  setController(controller);
}
