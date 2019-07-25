@Override public void update(Object args,Observable observable){
  if (args instanceof DcMetaComparator) {
    dcMetaChange((DcMetaComparator)args);
  }
 else   if (args instanceof DcRouteMetaComparator) {
    routeChanges();
  }
 else {
    throw new IllegalArgumentException(String.format("unknown args(%s):%s",args.getClass(),args));
  }
}
