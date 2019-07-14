/** 
 * Sets an  {@link EventHandler} that will be invoked when{@link ComponentHost#onInterceptTouchEvent} is called.
 * @param interceptTouchEventHandler the handler to be set on this host.
 */
void setInterceptTouchEventHandler(EventHandler<InterceptTouchEvent> interceptTouchEventHandler){
  mOnInterceptTouchEventHandler=interceptTouchEventHandler;
}
