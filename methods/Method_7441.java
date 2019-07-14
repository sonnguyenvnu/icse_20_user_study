public static WebFile createWithGeoPoint(double lat,double _long,long access_hash,int w,int h,int zoom,int scale){
  WebFile webFile=new WebFile();
  TLRPC.TL_inputWebFileGeoPointLocation location=new TLRPC.TL_inputWebFileGeoPointLocation();
  webFile.location=location;
  location.geo_point=webFile.geo_point=new TLRPC.TL_inputGeoPoint();
  location.access_hash=access_hash;
  webFile.geo_point.lat=lat;
  webFile.geo_point._long=_long;
  location.w=webFile.w=w;
  location.h=webFile.h=h;
  location.zoom=webFile.zoom=zoom;
  location.scale=webFile.scale=scale;
  webFile.mime_type="image/png";
  webFile.url=String.format(Locale.US,"maps_%.6f_%.6f_%d_%d_%d_%d.png",lat,_long,w,h,zoom,scale);
  webFile.attributes=new ArrayList<>();
  return webFile;
}
