@Override public void put(String id,DetectedActivity activity){
  SharedPreferences.Editor editor=preferences.edit();
  editor.putInt(getFieldKey(id,ACTIVITY_ID),activity.getType());
  editor.putInt(getFieldKey(id,CONFIDENCE_ID),activity.getConfidence());
  editor.apply();
}
