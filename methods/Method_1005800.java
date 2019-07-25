/** 
 * @return
 */
@RequestMapping("/setting/list.do") @ResponseBody @AuthPassport(authority=C_AUTH_SETTING) public JsonResult list(@ModelAttribute SettingQuery query) throws MyException {
  Page page=new Page(query);
  page.setAllRow(settingService.count(query));
  return new JsonResult().data(SettingAdapter.getDto(settingService.query(query))).page(page);
}
