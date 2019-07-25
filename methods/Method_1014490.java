@Override public void load(Document document){
  super.load(document);
  Document settlements=(Document)document.get("settlement_map");
  for (  String world : settlements.keySet()) {
    settlementMap.put(world,new Settlement((Document)settlements.get(world)));
  }
  LogManager.LOGGER.fine(String.format("(%s) Loaded %d settlements",name,settlementMap.size()));
}
