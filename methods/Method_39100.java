/** 
 * Adds a new child to the tree.
 */
public RouteChunk add(final String newValue){
  RouteChunk routeChunk=new RouteChunk(routes,this,newValue);
  if (children == null) {
    children=new RouteChunk[]{routeChunk};
  }
 else {
    children=ArraysUtil.append(children,routeChunk);
  }
  return routeChunk;
}
