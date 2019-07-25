@Override public void init(GameServer gameServer){
  listeners.add(new CpuInitialisationListener());
  listeners.add(new UserCreationListener());
  listeners.add(new ChargeShieldCommandListener());
  listeners.add(new SetInventoryPosition());
  listeners.add(new PutItemCommandListener());
  listeners.add(new PopItemCommandListener());
  listeners.add(new DeathListener());
  listeners.add(new WalkListener());
  GameRegistry registry=gameServer.getRegistry();
  registry.registerGameObject(Cubot.class);
  registry.registerHardware(CubotLeg.class);
  registry.registerHardware(CubotLaser.class);
  registry.registerHardware(CubotLidar.class);
  registry.registerHardware(CubotDrill.class);
  registry.registerHardware(CubotInventory.class);
  registry.registerHardware(CubotKeyboard.class);
  registry.registerHardware(CubotHologram.class);
  registry.registerHardware(CubotBattery.class);
  registry.registerHardware(CubotFloppyDrive.class);
  registry.registerHardware(CubotComPort.class);
  registry.registerHardware(CubotShield.class);
  registry.registerHardware(CubotCore.class);
  LogManager.LOGGER.info("(Cubot Plugin) Initialised Cubot plugin");
}
