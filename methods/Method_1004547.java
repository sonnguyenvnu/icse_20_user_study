/** 
 * @since 6.0.2
 */
private void show(final int pIndex){
  final int borderSizeInPixels=20;
  final double zoomFallback=12;
  final long animationSpeed=2000;
  final boolean animated=true;
  final DataRegion state=mList.get(pIndex % mList.size());
  final BoundingBox box=state.getBox();
  mMapView.zoomToBoundingBox(box,animated,borderSizeInPixels,zoomFallback,animationSpeed);
  Toast.makeText(getActivity(),state.getName(),Toast.LENGTH_SHORT).show();
}
