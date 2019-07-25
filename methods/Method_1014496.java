@Override public void init(GameServer gameServer){
  IServerConfiguration config=gameServer.getConfig();
  GameRegistry registry=gameServer.getRegistry();
  listeners.add(new WorldCreationListener());
  listeners.add(new WorldUpdateListener(config));
  listeners.add(new ObjectDeathListener(config));
  registry.registerGameObject(BiomassBlob.class);
  registry.registerItem(ItemBiomass.ID,ItemBiomass.class);
  LogManager.LOGGER.info("(BiomassPlugin) Initialised Biomass plugin");
}
