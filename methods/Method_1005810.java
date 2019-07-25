@RequestMapping("/synch.do") @ResponseBody @AuthPassport public JsonResult synch(@RequestBody String body) throws MyException {
  List<DebugInterfaceParamDto> list=JSON.parseArray(body,DebugInterfaceParamDto.class);
  LoginInfoDto user=LoginUserHelper.getUser();
  String projectId=MD5.encrytMD5(user.getId(),"").substring(0,20) + "-debug";
  Project project=projectService.getById(projectId);
  if (project == null) {
    project=buildProject(user,projectId);
    projectService.insert(project);
  }
  long moduleSequence=System.currentTimeMillis();
  for (  DebugInterfaceParamDto d : list) {
    String moduleId=d.getModuleId();
    if (d == null || MyString.isEmpty(moduleId)) {
      continue;
    }
    try {
      moduleId=Tools.handleId(user,moduleId);
      handelModule(user,project,moduleSequence,d,moduleId);
      moduleSequence=moduleSequence - 1;
      handelModuleIdAndDubugId(user,d,moduleId);
      deleteDebug(user,d,moduleId);
      int totalNum=debugService.count(new DebugQuery().setUserId(user.getId()));
      if (totalNum > 100) {
        return new JsonResult(MyError.E000058);
      }
      addDebug(user,d,totalNum);
    }
 catch (    Exception e) {
      e.printStackTrace();
      continue;
    }
  }
  List<Module> modules=moduleService.query(new ModuleQuery().setProjectId(projectId));
  List<String> moduleIds=new ArrayList<>();
  for (  Module m : modules) {
    moduleIds.add(m.getId());
  }
  List<Debug> debugs=debugService.query(new DebugQuery().setModuleIds(moduleIds));
  Map<String,List<DebugDto>> mapDebugs=new HashMap<>();
  for (  Debug d : debugs) {
    try {
      List<DebugDto> moduleDebugs=mapDebugs.get(d.getModuleId());
      if (moduleDebugs == null) {
        moduleDebugs=new ArrayList<>();
        mapDebugs.put(d.getModuleId(),moduleDebugs);
      }
      moduleDebugs.add(DebugAdapter.getDto(d));
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
  List<DebugInterfaceParamDto> returnlist=new ArrayList<DebugInterfaceParamDto>();
  for (  Module m : modules) {
    try {
      DebugInterfaceParamDto debugDto=new DebugInterfaceParamDto();
      debugDto.setModuleId(Tools.unhandleId(m.getId()));
      debugDto.setModuleName(m.getName());
      debugDto.setVersion(m.getVersion());
      debugDto.setStatus(m.getStatus());
      debugDto.setDebugs(mapDebugs.get(m.getId()) == null ? new ArrayList<DebugDto>() : mapDebugs.get(m.getId()));
      returnlist.add(debugDto);
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
  return new JsonResult(1,returnlist);
}
