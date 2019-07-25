/** 
 * Displays WebPopOver attached to the invoker component area and faced to specified direction. It will also be aligned using the specified alignment type when possible. WebPopOver opened in this way will always auto-follow invoker's ancestor window.
 * @param invoker        invoker component
 * @param boundsProvider source area provider
 * @param direction      preferred display direction
 * @param alignment      preferred display alignment
 * @return displayed WebPopOver
 */
public WebPopOver show(final Component invoker,final DataProvider<Rectangle> boundsProvider,final PopOverDirection direction,final PopOverAlignment alignment){
  if (isShowing()) {
    fireReopened();
  }
  final DataProvider<Rectangle> actualBoundsProvider=boundsProvider == null ? null : new DataProvider<Rectangle>(){
    @Override public Rectangle provide(){
      if (invoker.isShowing()) {
        final Rectangle bounds=boundsProvider.provide();
        final Point los=invoker.getLocationOnScreen();
        lastBounds=new Rectangle(los.x + bounds.x,los.y + bounds.y,bounds.width,bounds.height);
      }
      return lastBounds;
    }
  }
;
  attached=true;
  preferredDirection=direction != null ? direction : PopOverDirection.down;
  preferredAlignment=alignment;
  pack();
  updatePopOverLocation(invoker,actualBoundsProvider);
  installPopOverLocationUpdater(invoker,actualBoundsProvider);
  setVisible(true);
  fireOpened();
  return this;
}
