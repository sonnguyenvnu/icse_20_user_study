private void logUnmatchedRequest(LoggedRequest request){
  List<NearMiss> nearest=nearMissCalculator.findNearestTo(request);
  String message;
  if (!nearest.isEmpty()) {
    message=diffRenderer.render(nearest.get(0).getDiff());
  }
 else {
    message="Request was not matched as there were no stubs registered:\n" + request;
  }
  notifier().error(message);
}
