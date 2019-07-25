/** 
 * Get the session for an owner and create one if one doesn't exist.
 * @param owner the owner
 * @return a session
 */
public synchronized LocalSession get(SessionOwner owner){
  checkNotNull(owner);
  LocalSession session=getIfPresent(owner);
  LocalConfiguration config=worldEdit.getConfiguration();
  SessionKey sessionKey=owner.getSessionKey();
  if (session == null) {
    try {
      session=store.load(getKey(sessionKey));
      session.postLoad();
    }
 catch (    IOException e) {
      log.warn("Failed to load saved session",e);
      session=new LocalSession();
    }
    Request.request().setSession(session);
    session.setConfiguration(config);
    session.setBlockChangeLimit(config.defaultChangeLimit);
    session.setTimeout(config.calculationTimeout);
    try {
      if (owner.hasPermission("worldedit.selection.pos")) {
        setDefaultWand(session.getWandItem(),config.wandItem,session,new SelectionWand());
      }
      if (owner.hasPermission("worldedit.navigation.jumpto.tool") || owner.hasPermission("worldedit.navigation.thru.tool")) {
        setDefaultWand(session.getNavWandItem(),config.navigationWand,session,new NavigationWand());
      }
    }
 catch (    InvalidToolBindException e) {
      if (!warnedInvalidTool) {
        warnedInvalidTool=true;
        log.warn("Invalid wand tool set in config. Tool will not be assigned: " + e.getItemType());
      }
    }
    sessions.put(getKey(owner),new SessionHolder(sessionKey,session));
  }
  if (shouldBoundLimit(owner,"worldedit.limit.unrestricted",session.getBlockChangeLimit(),config.maxChangeLimit)) {
    session.setBlockChangeLimit(config.maxChangeLimit);
  }
  if (shouldBoundLimit(owner,"worldedit.timeout.unrestricted",session.getTimeout(),config.maxCalculationTimeout)) {
    session.setTimeout(config.maxCalculationTimeout);
  }
  session.setUseInventory(config.useInventory && !(config.useInventoryOverride && (owner.hasPermission("worldedit.inventory.unrestricted") || (config.useInventoryCreativeOverride && (!(owner instanceof Player) || ((Player)owner).getGameMode() == GameModes.CREATIVE)))));
  return session;
}
