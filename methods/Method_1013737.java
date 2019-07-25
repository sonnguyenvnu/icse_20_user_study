@Override public List<RouteMeta> routes(String currentDc,String tag){
  DcMeta dcMeta=getDirectDcMeta(currentDc);
  List<RouteMeta> routes=dcMeta.getRoutes();
  List<RouteMeta> result=new LinkedList<>();
  if (routes != null) {
    routes.forEach(routeMeta -> {
      if (routeMeta.tagEquals(tag) && currentDc.equalsIgnoreCase(routeMeta.getSrcDc())) {
        result.add(MetaClone.clone(routeMeta));
      }
    }
);
  }
  return result;
}
