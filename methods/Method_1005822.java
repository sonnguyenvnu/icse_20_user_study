@RequestMapping("/invite.do") public String invite(@RequestParam String code,HttpServletRequest request,HttpServletResponse response) throws Exception {
  String projectId=projectService.getProjectIdFromInviteCode(code);
  Project project=projectService.getById(projectId);
  if (project == null) {
    request.setAttribute("result","?????????????????");
    return ERROR_VIEW;
  }
  LoginInfoDto loginInfoDto=LoginUserHelper.tryGetUser();
  if (loginInfoDto == null) {
    response.sendRedirect("/loginOrRegister.do#/login");
    return null;
  }
  String userId=loginInfoDto.getId();
  if (projectUserService.count(new ProjectUserQuery().setUserId(userId).setProjectId(projectId)) > 0) {
    request.setAttribute("result",MyError.E000039.getMessage());
    return ERROR_VIEW;
  }
  if (userId.equals(project.getUserId())) {
    request.setAttribute("result","??????????");
    return ERROR_VIEW;
  }
  ProjectUserPO projectUser=new ProjectUserPO();
  projectUser.setProjectId(projectId);
  projectUser.setUserId(userId);
  projectUser.setStatus(ProjectUserStatus.NORMAL.getStatus());
  projectUser.setUserEmail(loginInfoDto.getEmail());
  projectUser.setUserName(loginInfoDto.getUserName());
  StringBuilder sb=new StringBuilder(",");
  for (  ProjectPermissionEnum permissionEnum : ProjectPermissionEnum.values()) {
    if (ProjectPermissionEnum.isDefaultPermission(permissionEnum)) {
      sb.append(permissionEnum.getValue() + ",");
    }
  }
  projectUser.setPermission(sb.toString());
  projectUserService.insert(projectUser);
  request.setAttribute("title","????");
  request.setAttribute("result","????");
  return ERROR_VIEW;
}
