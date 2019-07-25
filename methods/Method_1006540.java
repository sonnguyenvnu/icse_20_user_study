private void callback(RouteResponse response){
  if (response.getStatus() != RouteStatus.SUCCEED) {
    RLog.w(response.getMessage());
  }
  if (mRouteRequest.getRouteCallback() != null) {
    mRouteRequest.getRouteCallback().callback(response.getStatus(),mRouteRequest.getUri(),response.getMessage());
  }
}
