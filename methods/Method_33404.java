public void restore(){
  if (isCached.getAndSet(false)) {
    node.setCache(cache);
    node.setCacheHint(cacheHint);
    if (node instanceof Region) {
      ((Region)node).setCacheShape(cacheShape);
      ((Region)node).setSnapToPixel(snapToPixel);
    }
  }
}
