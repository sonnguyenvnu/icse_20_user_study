@Override public void handle(GameEvent event){
  if (event.getSource().getClass().getCanonicalName().equals("net.simon987.npcplugin.HarvesterNPC")) {
    GameObject dyingHarvesterNPC=(GameObject)event.getSource();
    if (dyingHarvesterNPC.getX() != 0 && dyingHarvesterNPC.getX() != dyingHarvesterNPC.getWorld().getWorldSize() - 1 && dyingHarvesterNPC.getY() != 0 && dyingHarvesterNPC.getY() != dyingHarvesterNPC.getWorld().getWorldSize() - 1) {
      BiomassBlob newBiomassBlob=createBiomassBlobAt(dyingHarvesterNPC.getX(),dyingHarvesterNPC.getY(),dyingHarvesterNPC.getWorld());
      dyingHarvesterNPC.getWorld().addObject(newBiomassBlob);
    }
  }
}
