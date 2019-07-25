@RequestMapping("/list.do") @ResponseBody @AuthPassport public JsonResult list(@ModelAttribute CommentQuery query) throws MyException {
  Assert.notNull(query.getTargetId());
  Assert.notNull(query.getType());
  String projectId;
  if (query.getType().equals(CommentType.ARTICLE.getType())) {
    projectId=articleService.getById(query.getTargetId()).getProjectId();
  }
 else {
    projectId=bugService.get(query.getTargetId()).getProjectId();
  }
  checkPermission(projectId,ProjectPermissionEnum.READ);
  query.setPageSize(10);
  query.setSort(TableField.SORT.SEQUENCE_DESC);
  List<CommentPO> commentList=commentService.select(query);
  Page page=new Page(query);
  page.setAllRow(commentService.count(query));
  return new JsonResult(1,CommentAdapter.getDto(commentList),page);
}
