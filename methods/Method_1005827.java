@RequestMapping("/list.do") @ResponseBody public JsonResult list(@ModelAttribute CommentQuery query) throws Exception {
  if (query.getTargetId() == null) {
    return new JsonResult().data(Lists.newArrayList()).page(new Page(query));
  }
  Assert.notNull(query.getType(),"type ????");
  query.setPageSize(10);
  query.setSort(TableField.SORT.CREATE_TIME_DES);
  List<CommentPO> commentPOList=commentService.select(query);
  Page page=new Page(query);
  page.setAllRow(commentService.count(query));
  return new JsonResult().success().data(CommentAdapter.getDto(commentPOList)).page(page);
}
