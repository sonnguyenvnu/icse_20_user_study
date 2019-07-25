@RequestMapping("/admin/hotSearch/detail.do") @ResponseBody @AuthPassport(authority=C_HOT_SEARCH) public JsonResult detail(String id){
  HotSearch hotSearch=new HotSearch();
  if (id != null) {
    hotSearch=hotSearchService.getById(id);
  }
  return new JsonResult().data(HotSearchAdapter.getDto(hotSearch));
}
