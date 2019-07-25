/** 
 * bug??
 * @return
 * @throws MyException
 */
@RequestMapping("/list.do") @ResponseBody @AuthPassport public JsonResult list(@ModelAttribute BugQuery query) throws Exception {
  Project project=getProject(query);
  checkPermission(project,ProjectPermissionEnum.READ);
  query.setPageSize(10);
  List<BugPO> bugPOList=bugService.select(query);
  Page page=new Page(query);
  page.setAllRow(bugService.count(query));
  List<BugDTO> dtoList=BugAdapter.getDto(bugPOList);
  return new JsonResult().data(dtoList).page(page);
}
