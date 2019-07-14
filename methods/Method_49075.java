static boolean validJanusGraphHas(Iterable<HasContainer> has){
  for (  final HasContainer h : has) {
    if (!validJanusGraphHas(h))     return false;
  }
  return true;
}
