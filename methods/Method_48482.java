public Direction parseDirection(Entry data){
  RelationCache map=data.getCache();
  if (map != null)   return map.direction;
  return IDHandler.readRelationType(data.asReadBuffer()).dirID.getDirection();
}
