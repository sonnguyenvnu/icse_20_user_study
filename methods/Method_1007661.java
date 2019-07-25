@Command(name="version",aliases={"ver"},desc="Get WorldEdit version") public void version(Actor actor){
  actor.print("WorldEdit version " + WorldEdit.getVersion());
  actor.print("https://github.com/EngineHub/worldedit/");
  PlatformManager pm=we.getPlatformManager();
  actor.printDebug("----------- Platforms -----------");
  for (  Platform platform : pm.getPlatforms()) {
    actor.printDebug(String.format("* %s (%s)",platform.getPlatformName(),platform.getPlatformVersion()));
  }
  actor.printDebug("----------- Capabilities -----------");
  for (  Capability capability : Capability.values()) {
    Platform platform=pm.queryCapability(capability);
    actor.printDebug(String.format("%s: %s",capability.name(),platform != null ? platform.getPlatformName() : "NONE"));
  }
}
