@Override public void put(String id,GeofenceModel geofenceModel){
  SharedPreferences.Editor editor=preferences.edit();
  editor.putLong(getFieldKey(id,LATITUDE_ID),Double.doubleToLongBits(geofenceModel.getLatitude()));
  editor.putLong(getFieldKey(id,LONGITUDE_ID),Double.doubleToLongBits(geofenceModel.getLongitude()));
  editor.putFloat(getFieldKey(id,RADIUS_ID),geofenceModel.getRadius());
  editor.putInt(getFieldKey(id,TRANSITION_ID),geofenceModel.getTransition());
  editor.putLong(getFieldKey(id,EXPIRATION_ID),geofenceModel.getExpiration());
  editor.putInt(getFieldKey(id,LOITERING_DELAY_ID),geofenceModel.getLoiteringDelay());
  editor.apply();
}
