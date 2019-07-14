/** 
 * Fills side breezemoons.
 * @param dataModel the specified data model
 */
private void fillSideBreezemoons(final Map<String,Object> dataModel){
  Stopwatchs.start("Fills breezemoons");
  try {
    final int avatarViewMode=Sessions.getAvatarViewMode();
    final List<JSONObject> sideBreezemoons=breezemoonQueryService.getSideBreezemoons(avatarViewMode);
    dataModel.put(Common.SIDE_BREEZEMOONS,sideBreezemoons);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Fill side breezemoons failed",e);
  }
 finally {
    Stopwatchs.end();
  }
}
