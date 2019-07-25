/** 
 * Called every tick <br>The fist time this is called, NPCs retrieved from the database are linked to the Factory
 */
@Override public void update(){
  Settlement settlement=NpcPlugin.settlementMap.get(getWorld().getId());
  if (settlement == null) {
    getWorld().decUpdatable();
    setDead(true);
    return;
  }
  if (cooldown == 0) {
    if (settlement.getNpcs().size() < MAX_NPC_COUNT) {
      Point p=getAdjacentTile();
      if (p != null) {
        NonPlayerCharacter npc=spawnNPC(p);
        settlement.addNpc(npc);
        getWorld().addObject(npc);
        getWorld().incUpdatable();
      }
    }
    cooldown+=NPC_CREATION_COOLDOWN;
  }
 else {
    cooldown--;
  }
}
