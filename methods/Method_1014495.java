@Override public void initialize(){
  homeWorld=GameServer.INSTANCE.getGameUniverse().getWorld(0x7FFF,0x7FFF,false,"v" + getObjectId() + "-");
  if (homeWorld == null) {
    VaultDimension vaultDimension=new VaultDimension(this);
    homeWorld=vaultDimension.getHomeWorld();
    homeX=vaultDimension.getHomeX();
    homeY=vaultDimension.getHomeY();
  }
}
