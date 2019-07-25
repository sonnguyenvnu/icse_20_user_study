/** 
 * ????
 * @param request
 * @return
 */
@SuppressWarnings("unchecked") @RequestMapping(params="menu") public void menu(HttpServletRequest request,HttpServletResponse response){
  SetListSort sort=new SetListSort();
  TSUser u=ResourceUtil.getSessionUser();
  Set<TSFunction> loginActionlist=new HashSet<TSFunction>();
  List<TSRoleUser> rUsers=systemService.findByProperty(TSRoleUser.class,"TSUser.id",u.getId());
  for (  TSRoleUser ru : rUsers) {
    TSRole role=ru.getTSRole();
    List<TSRoleFunction> roleFunctionList=systemService.findByProperty(TSRoleFunction.class,"TSRole.id",role.getId());
    if (roleFunctionList.size() > 0) {
      for (      TSRoleFunction roleFunction : roleFunctionList) {
        TSFunction function=(TSFunction)roleFunction.getTSFunction();
        loginActionlist.add(function);
      }
    }
  }
  List<TSFunction> bigActionlist=new ArrayList<TSFunction>();
  List<TSFunction> smailActionlist=new ArrayList<TSFunction>();
  if (loginActionlist.size() > 0) {
    for (    TSFunction function : loginActionlist) {
      if (function.getFunctionLevel() == 0) {
        bigActionlist.add(function);
      }
 else       if (function.getFunctionLevel() == 1) {
        smailActionlist.add(function);
      }
    }
  }
  Collections.sort(bigActionlist,sort);
  Collections.sort(smailActionlist,sort);
  String logString=ListtoMenu.getMenu(bigActionlist,smailActionlist);
  try {
    response.getWriter().write(logString);
    response.getWriter().flush();
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
 finally {
    try {
      response.getWriter().close();
    }
 catch (    IOException e) {
      e.printStackTrace();
    }
  }
}
