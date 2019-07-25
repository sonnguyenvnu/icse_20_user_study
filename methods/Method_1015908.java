public void init(){
  this.entityRewrite=EntityMap.getEntityMap(getPendingConnection().getVersion());
  this.displayName=name;
  tabListHandler=new ServerUnique(this);
  Collection<String> g=bungee.getConfigurationAdapter().getGroups(name);
  g.addAll(bungee.getConfigurationAdapter().getGroups(getUniqueId().toString()));
  for (  String s : g) {
    addGroups(s);
  }
  forgeClientHandler=new ForgeClientHandler(this);
  forgeClientHandler.setFmlTokenInHandshake(this.getPendingConnection().getExtraDataInHandshake().contains(ForgeConstants.FML_HANDSHAKE_TOKEN));
}
