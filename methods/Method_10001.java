/** 
 * Fills footer.
 * @param dataModel the specified data model
 */
private void fillFooter(final Map<String,Object> dataModel){
  fillSysInfo(dataModel);
  dataModel.put(Common.YEAR,String.valueOf(Calendar.getInstance().get(Calendar.YEAR)));
  dataModel.put(Common.SITE_VISIT_STAT_CODE,Symphonys.SITE_VISIT_STATISTIC_CODE);
  dataModel.put(Common.MOUSE_EFFECTS,RandomUtils.nextDouble() > 0.95);
  dataModel.put(Common.FOOTER_BEI_AN_HAO,Symphonys.FOOTER_BEIANHAO);
}
