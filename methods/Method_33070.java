/** 
 * This method will change the drawer behavior according to the argument direction.
 * @param dir - The direction that the drawer will enter the screen from.
 */
private void updateDirection(DrawerDirection dir){
  maxSizeProperty.set(-1);
  prefSizeProperty.set(-1);
  translateProperty.set(0);
  if (dir == DrawerDirection.LEFT || dir == DrawerDirection.RIGHT) {
    translateProperty=sidePane.translateXProperty();
    maxSizeProperty=sidePane.maxWidthProperty();
    prefSizeProperty=sidePane.prefWidthProperty();
    sizeProperty=sidePane.widthProperty();
    paddingSizeProperty=paddingPane.minWidthProperty();
  }
 else   if (dir == DrawerDirection.TOP || dir == DrawerDirection.BOTTOM) {
    translateProperty=sidePane.translateYProperty();
    maxSizeProperty=sidePane.maxHeightProperty();
    prefSizeProperty=sidePane.prefHeightProperty();
    sizeProperty=sidePane.heightProperty();
    paddingSizeProperty=paddingPane.minHeightProperty();
  }
  if (dir == DrawerDirection.LEFT) {
    StackPane.setAlignment(sidePane,Pos.CENTER_LEFT);
  }
 else   if (dir == DrawerDirection.RIGHT) {
    StackPane.setAlignment(sidePane,Pos.CENTER_RIGHT);
  }
 else   if (dir == DrawerDirection.TOP) {
    StackPane.setAlignment(sidePane,Pos.TOP_CENTER);
  }
 else   if (dir == DrawerDirection.BOTTOM) {
    StackPane.setAlignment(sidePane,Pos.BOTTOM_CENTER);
  }
  setDefaultDrawerSize(getDefaultDrawerSize());
  updateDrawerAnimation(initTranslate.get());
  updateContent();
  setMiniDrawerSize(getMiniDrawerSize());
}
