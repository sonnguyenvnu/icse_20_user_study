/** 
 * @since 6.0.0
 */
private void refresh(){
  fromPixels((mIntrinsicScreenRectProjection.left + mIntrinsicScreenRectProjection.right) / 2,(mIntrinsicScreenRectProjection.top + mIntrinsicScreenRectProjection.bottom) / 2,mCurrentCenter);
  IGeoPoint neGeoPoint=fromPixels(mIntrinsicScreenRectProjection.right,mIntrinsicScreenRectProjection.top,null,true);
  final TileSystem tileSystem=org.osmdroid.views.MapView.getTileSystem();
  if (neGeoPoint.getLatitude() > tileSystem.getMaxLatitude()) {
    neGeoPoint=new GeoPoint(tileSystem.getMaxLatitude(),neGeoPoint.getLongitude());
  }
  if (neGeoPoint.getLatitude() < tileSystem.getMinLatitude()) {
    neGeoPoint=new GeoPoint(tileSystem.getMinLatitude(),neGeoPoint.getLongitude());
  }
  IGeoPoint swGeoPoint=fromPixels(mIntrinsicScreenRectProjection.left,mIntrinsicScreenRectProjection.bottom,null,true);
  if (swGeoPoint.getLatitude() > tileSystem.getMaxLatitude()) {
    swGeoPoint=new GeoPoint(tileSystem.getMaxLatitude(),swGeoPoint.getLongitude());
  }
  if (swGeoPoint.getLatitude() < tileSystem.getMinLatitude()) {
    swGeoPoint=new GeoPoint(tileSystem.getMinLatitude(),swGeoPoint.getLongitude());
  }
  mBoundingBoxProjection.set(neGeoPoint.getLatitude(),neGeoPoint.getLongitude(),swGeoPoint.getLatitude(),swGeoPoint.getLongitude());
  if (mOrientation != 0 && mOrientation != 180) {
    GeometryMath.getBoundingBoxForRotatatedRectangle(mIntrinsicScreenRectProjection,getScreenCenterX(),getScreenCenterY(),mOrientation,mScreenRectProjection);
  }
 else {
    mScreenRectProjection.left=mIntrinsicScreenRectProjection.left;
    mScreenRectProjection.top=mIntrinsicScreenRectProjection.top;
    mScreenRectProjection.right=mIntrinsicScreenRectProjection.right;
    mScreenRectProjection.bottom=mIntrinsicScreenRectProjection.bottom;
  }
}
