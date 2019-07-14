@Override public Predicate<StaticBuffer> getKeyFilter(){
  if (isGlobalGraphIndex()) {
    assert graphIndexId > 0;
    return (k -> {
      try {
        return indexSerializer.getIndexIdFromKey(k) == graphIndexId;
      }
 catch (      RuntimeException e) {
        log.error("Filtering key {} due to exception",k,e);
        return false;
      }
    }
);
  }
 else {
    return buffer -> !IDManager.VertexIDType.Invisible.is(idManager.getKeyID(buffer));
  }
}
