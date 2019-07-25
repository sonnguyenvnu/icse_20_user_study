public synchronized void update(Markup markup){
  if (markup == null)   return;
  if (markup instanceof MarkerItemMarkup) {
    MarkerItem item=getByUid(markup.getId());
    if (item != null) {
      mapView.items().removeItem(item);
    }
    item=((MarkerItemMarkup)markup).getMarkerItem(context);
    if (item != null) {
      mapView.items().addItem(item);
    }
  }
 else   if (markup instanceof DrawableMarkup) {
    updateDrawableLayer();
    mapView.drawables().update();
  }
  redraw();
}
