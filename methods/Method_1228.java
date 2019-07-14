/** 
 * Sets the image request
 * @param request Image Request
 */
public void setImageRequest(ImageRequest request){
  AbstractDraweeControllerBuilder controllerBuilder=mControllerBuilder;
  DraweeController controller=controllerBuilder.setImageRequest(request).setOldController(getController()).build();
  setController(controller);
}
