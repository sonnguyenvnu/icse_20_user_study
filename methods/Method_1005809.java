@RequestMapping("/detail.do") @ResponseBody @AuthPassport public JsonResult detail(String id) throws MyException {
  CommentPO dbComment=commentService.get(id);
  String projectId;
  if (dbComment.getType().equals(CommentType.ARTICLE.getType())) {
    projectId=articleService.getById(dbComment.getTargetId()).getProjectId();
  }
 else {
    projectId=bugService.get(dbComment.getTargetId()).getProjectId();
  }
  checkPermission(projectId,ProjectPermissionEnum.READ);
  return new JsonResult().data(CommentAdapter.getDto(dbComment));
}
