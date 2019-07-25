public static void stop(){
  SmartLocation.with(OmniNotes.getAppContext()).location().stop();
  if (Geocoder.isPresent()) {
    SmartLocation.with(OmniNotes.getAppContext()).geocoding().stop();
  }
}
