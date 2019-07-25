public void insert(LatLon p){
  int x=longitudeToCellX(p.getLongitude());
  int y=latitudeToCellY(p.getLatitude());
  checkBounds(x,y);
  ArrayList<LatLon> list=raster[y * rasterWidth + x];
  if (list == null) {
    list=new ArrayList<>();
    raster[y * rasterWidth + x]=list;
  }
  list.add(p);
  size++;
}
