/** 
 * ????
 */
public RxGalleryFinal subscribe(@NonNull RxBusResultDisposable<? extends BaseResultEvent> rxBusResultSubscriber){
  this.isRadioDisposable=(RxBusResultDisposable<BaseResultEvent>)rxBusResultSubscriber;
  return this;
}
