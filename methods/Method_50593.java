public String getLocation(){
  if (hasRealLoc()) {
    return String.valueOf(node.getLoc());
  }
 else {
    return "no location";
  }
}
