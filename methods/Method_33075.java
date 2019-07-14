/** 
 * this method is only used in drawers stack component
 * @param callback
 */
void bringToFront(Callback<Void,Void> callback){
  EventHandler<? super MouseEvent> eventFilter=Event::consume;
  final boolean fillSize=prefSizeProperty.get() == USE_COMPUTED_SIZE;
  this.addEventFilter(MouseEvent.ANY,eventFilter);
  Runnable onFinished=() -> {
    callback.call(null);
    translateTo=0;
    translateTimer.setOnFinished(() -> {
      if (fillSize) {
        prefSizeProperty.set(USE_COMPUTED_SIZE);
        maxSizeProperty.set(USE_COMPUTED_SIZE);
      }
      this.removeEventFilter(MouseEvent.ANY,eventFilter);
    }
);
    getCachePolicy().cache(contentHolder);
    translateTimer.start();
  }
;
  if (sizeProperty.get() > getDefaultDrawerSize()) {
    tempDrawerSize=sizeProperty.get();
  }
 else {
    tempDrawerSize=getDefaultDrawerSize();
  }
  translateTo=initTranslate.get();
  translateTimer.setOnFinished(onFinished);
  getCachePolicy().cache(contentHolder);
  translateTimer.start();
}
