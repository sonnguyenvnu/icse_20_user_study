/** 
 * set the dialog container Note: the dialog container must be StackPane, its the container for the dialog to be shown in.
 * @param dialogContainer
 */
public void setDialogContainer(StackPane dialogContainer){
  if (dialogContainer != null) {
    this.dialogContainer=dialogContainer;
    offsetX=dialogContainer.getBoundsInLocal().getWidth();
    offsetY=dialogContainer.getBoundsInLocal().getHeight();
    animation=getShowAnimation(transitionType.get());
  }
}
