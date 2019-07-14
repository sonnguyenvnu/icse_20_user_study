private void onLocationUnavailable(){
  if (foundContextBot != null && foundContextBot.bot_inline_geo) {
    lastKnownLocation=new Location("network");
    lastKnownLocation.setLatitude(-1000);
    lastKnownLocation.setLongitude(-1000);
    searchForContextBotResults(true,foundContextBot,searchingContextQuery,"");
  }
}
