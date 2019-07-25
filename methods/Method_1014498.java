@Override public void handle(GameEvent event){
  int minCount=GameServer.INSTANCE.getConfig().getInt("minBiomassCount");
  int maxCount=GameServer.INSTANCE.getConfig().getInt("maxBiomassCount");
  int yield=GameServer.INSTANCE.getConfig().getInt("biomass_yield");
  ArrayList<BiomassBlob> biomassBlobs=WorldUtils.generateBlobs(((WorldGenerationEvent)event).getWorld(),minCount,maxCount,yield);
  for (  BiomassBlob blob : biomassBlobs) {
    ((WorldGenerationEvent)event).getWorld().addObject(blob);
  }
}
