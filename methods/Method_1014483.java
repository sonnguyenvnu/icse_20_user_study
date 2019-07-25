@Override public void init(GameServer gameServer){
  listeners.add(new CpuInitialisationListener());
  GameRegistry registry=gameServer.getRegistry();
  registry.registerHardware(RandomNumberGenerator.class);
  registry.registerHardware(Clock.class);
  LogManager.LOGGER.info("(Mist HW Plugin) Initialised Misc Hardware Plugin");
}
