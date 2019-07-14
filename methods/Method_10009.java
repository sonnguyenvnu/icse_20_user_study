/** 
 * Fills the header banner.
 * @param dataModel the specified data model
 */
private void fillHeaderBanner(final Map<String,Object> dataModel){
  final JSONObject adOption=optionQueryService.getOption(Option.ID_C_HEADER_BANNER);
  if (null == adOption) {
    dataModel.put("HeaderBannerLabel","");
  }
 else {
    dataModel.put("HeaderBannerLabel",adOption.optString(Option.OPTION_VALUE));
  }
}
