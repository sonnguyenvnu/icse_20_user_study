@RequestMapping("/detail.do") @ResponseBody @AuthPassport public JsonResult detail(String id) throws MyException {
  Assert.notNull(id);
  ProjectUserPO projectUser=projectUserService.get(id);
  Project project=projectCache.get(projectUser.getProjectId());
  checkPermission(project);
  ProjectUserDto projectUserDto=ProjectUserAdapter.getDto(projectUser,project);
  return new JsonResult(1,projectUserDto);
}
