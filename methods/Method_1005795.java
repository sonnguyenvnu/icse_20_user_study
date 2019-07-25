@RequestMapping("/admin/hotSearch/list.do") @ResponseBody @AuthPassport(authority=C_HOT_SEARCH) public JsonResult list(@ModelAttribute HotSearchQuery query) throws MyException {
  Page page=new Page(query);
  page.setAllRow(hotSearchService.count(query));
  return new JsonResult(1,HotSearchAdapter.getDto(hotSearchService.query(query)),page);
}
