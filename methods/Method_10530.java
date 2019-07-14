private void getLocation(){
  if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
    ActivityCompat.requestPermissions(mContext,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},1);
    return;
  }
  locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,0,this);
  locationManager.addGpsStatusListener(new GpsStatus.Listener(){
    @Override public void onGpsStatusChanged(    int event){
switch (event) {
case GpsStatus.GPS_EVENT_STARTED:
        System.out.println("GPS_EVENT_STARTED");
      mGpsCount.setText("0");
    break;
case GpsStatus.GPS_EVENT_FIRST_FIX:
  System.out.println("GPS_EVENT_FIRST_FIX");
break;
case GpsStatus.GPS_EVENT_SATELLITE_STATUS:
System.out.println("GPS_EVENT_SATELLITE_STATUS");
if (ActivityCompat.checkSelfPermission(mContext,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
return;
}
GpsStatus gpsStatus=locationManager.getGpsStatus(null);
Iterable<GpsSatellite> gpsSatellites=gpsStatus.getSatellites();
int count=0;
Iterator iterator=gpsSatellites.iterator();
while (iterator.hasNext()) {
count++;
iterator.next();
}
mGpsCount.setText(count + "");
break;
case GpsStatus.GPS_EVENT_STOPPED:
System.out.println("GPS_EVENT_STOPPED");
break;
}
}
}
);
}
