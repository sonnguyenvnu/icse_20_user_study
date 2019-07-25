@Override public void put(String id,Location location){
  SharedPreferences.Editor editor=preferences.edit();
  editor.putString(getFieldKey(id,PROVIDER_ID),location.getProvider());
  editor.putLong(getFieldKey(id,LATITUDE_ID),Double.doubleToLongBits(location.getLatitude()));
  editor.putLong(getFieldKey(id,LONGITUDE_ID),Double.doubleToLongBits(location.getLongitude()));
  editor.putFloat(getFieldKey(id,ACCURACY_ID),location.getAccuracy());
  editor.putLong(getFieldKey(id,ALTITUDE_ID),Double.doubleToLongBits(location.getAltitude()));
  editor.putFloat(getFieldKey(id,SPEED_ID),location.getSpeed());
  editor.putLong(getFieldKey(id,TIME_ID),location.getTime());
  editor.putFloat(getFieldKey(id,BEARING_ID),location.getBearing());
  editor.apply();
}
