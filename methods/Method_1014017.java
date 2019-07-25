public boolean apply(ReadyMarker readyMarker){
  return isTracked(type,readyMarker.getType()) && isTracked(identifier,readyMarker.getIdentifier());
}
