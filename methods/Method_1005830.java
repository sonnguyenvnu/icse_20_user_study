@RequestMapping("/menu.do") @ResponseBody public JsonResult menu(@RequestParam String projectId) throws MyException {
  throwExceptionWhenIsNull(projectId,"projectId");
  Project project=projectCache.get(projectId);
  if (project.getType() == ProjectType.PRIVATE.getType()) {
    LoginInfoDto user=LoginUserHelper.getUser(MyError.E000041);
    if (!Tools.isSuperAdmin(user.getAuthStr()) && !user.getId().equals(project.getUserId())) {
      List<ProjectUserPO> projectUserPOList=projectUserService.select(new ProjectUserQuery().setProjectId(project.getId()).setUserId(user.getId()));
      if (CollectionUtils.isEmpty(projectUserPOList)) {
        throw new MyException(MyError.E000042);
      }
    }
  }
  List<ModuleDto> moduleDtoList=ModuleAdapter.getDto(moduleService.query(new ModuleQuery().setProjectId(projectId).setPageSize(10)),null);
  return new JsonResult(1,moduleDtoList,null,Tools.getMap("project",ProjectAdapter.getDto(project,null)));
}
