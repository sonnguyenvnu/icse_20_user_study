/** 
 * Fills the side tips.
 * @param dataModel the specified data model
 */
private void fillSideTips(final Map<String,Object> dataModel){
  if (RandomUtils.nextFloat() < 0.8) {
    return;
  }
  final List<String> tipsLabels=new ArrayList<>();
  final Map<String,String> labels=langPropsService.getAll(Locales.getLocale());
  for (  final Map.Entry<String,String> entry : labels.entrySet()) {
    final String key=entry.getKey();
    if (key.startsWith("tips")) {
      tipsLabels.add(entry.getValue());
    }
  }
  dataModel.put("tipsLabel",tipsLabels.get(RandomUtils.nextInt(tipsLabels.size())));
}
