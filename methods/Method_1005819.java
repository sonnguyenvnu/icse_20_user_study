@RequestMapping("/list.do") @ResponseBody @AuthPassport public JsonResult list(@ModelAttribute ProjectUserQuery query) throws MyException {
  Assert.notNull(query.getProjectId());
  checkPermission(projectCache.get(query.getProjectId()));
  List<ProjectUserPO> projectUsers=projectUserService.select(query);
  Page page=new Page(query);
  page.setAllRow(projectUserService.count(query));
  List<ProjectUserDto> dto=ProjectUserAdapter.getDto(projectUsers);
  return new JsonResult(1,dto,page);
}
