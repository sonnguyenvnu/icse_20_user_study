@Override public void run(){
  AstroThingHandler astroHandler=AstroHandlerFactory.getHandler(getThingUID());
  if (checkNull(astroHandler,"AstroThingHandler is null")) {
    return;
  }
  Channel phaseNameChannel=astroHandler.getThing().getChannel(CHANNEL_ID_SUN_PHASE_NAME);
  if (checkNull(phaseNameChannel,"Phase Name Channel is null")) {
    return;
  }
  Planet planet=astroHandler.getPlanet();
  if (planet != null && planet instanceof Sun) {
    final Sun typedSun=(Sun)planet;
    typedSun.getPhase().setName(sunPhaseName);
    astroHandler.publishChannelIfLinked(phaseNameChannel.getUID());
  }
}
