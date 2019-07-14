public boolean positionAllowed(PropertyIdValue pid,SnakPosition position){
  if (position.equals(SnakPosition.MAINSNAK)) {
    return _fetcher.allowedAsValue(pid);
  }
 else   if (position.equals(SnakPosition.QUALIFIER)) {
    return _fetcher.allowedAsQualifier(pid);
  }
 else   if (position.equals(SnakPosition.REFERENCE)) {
    return _fetcher.allowedAsReference(pid);
  }
  return true;
}
