@Override public StdDateFormat clone(){
  return new StdDateFormat(_timezone,_locale,_lenient,_tzSerializedWithColon);
}
