@Override public void drawCodeArea(final OpenLocationCode code){
  if (code.equals(mLastDisplayedCode)) {
    return;
  }
  mLastDisplayedCode=code;
  if (mMap != null) {
    CodeArea area=code.decode();
    LatLng southWest=new LatLng(area.getSouthLatitude(),area.getWestLongitude());
    LatLng northWest=new LatLng(area.getNorthLatitude(),area.getWestLongitude());
    LatLng southEast=new LatLng(area.getSouthLatitude(),area.getEastLongitude());
    LatLng northEast=new LatLng(area.getNorthLatitude(),area.getEastLongitude());
    if (mCodePolygon != null) {
      mCodePolygon.remove();
    }
    mCodePolygon=mMap.addPolygon(new PolygonOptions().add(southWest,northWest,northEast,southEast).strokeColor(ContextCompat.getColor(this.getContext(),R.color.code_box_stroke)).strokeWidth(CODE_AREA_STROKE_WIDTH).fillColor(ContextCompat.getColor(this.getContext(),R.color.code_box_fill)));
  }
}
