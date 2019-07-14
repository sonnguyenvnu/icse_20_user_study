public boolean hasRealLoc(){
  try {
    Location loc=node.getLoc();
    return loc != null && Locations.isReal(loc);
  }
 catch (  UnexpectedCodePathException e) {
    return false;
  }
catch (  IndexOutOfBoundsException|NullPointerException e) {
    return false;
  }
}
