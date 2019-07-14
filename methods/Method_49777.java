/** 
 * Find the route from a Collection of routes that best matches a given address. May return null if no routes are applicable.
 * @param routes a Collection of RouteInfos to chose from
 * @param dest   the InetAddress your trying to get to
 * @return the RouteInfo from the Collection that best fits the given address
 */
public static RouteInfo selectBestRoute(Collection<RouteInfo> routes,InetAddress dest){
  if ((routes == null) || (dest == null))   return null;
  RouteInfo bestRoute=null;
  for (  RouteInfo route : routes) {
    if (NetworkUtilsHelper.addressTypeMatches(route.mDestination.getAddress(),dest)) {
      if ((bestRoute != null) && (bestRoute.mDestination.getNetworkPrefixLength() >= route.mDestination.getNetworkPrefixLength())) {
        continue;
      }
      if (route.matches(dest))       bestRoute=route;
    }
  }
  return bestRoute;
}
