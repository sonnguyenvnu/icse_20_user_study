/** 
 * Return two lists, a list of routes that would be removed from mRoutes and a list of routes that would be added to mRoutes which would then result in target and mRoutes being the same list.
 * @param target is a LinkProperties with the new list of routes
 * @return the removed and added lists.
 */
public CompareResult<RouteInfo> compareRoutes(LinkProperties target){
  CompareResult<RouteInfo> result=new CompareResult<RouteInfo>();
  result.removed=new ArrayList<RouteInfo>(mRoutes);
  result.added.clear();
  if (target != null) {
    for (    RouteInfo r : target.getRoutes()) {
      if (!result.removed.remove(r)) {
        result.added.add(r);
      }
    }
  }
  return result;
}
