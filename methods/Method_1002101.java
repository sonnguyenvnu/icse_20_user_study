/** 
 * ???????
 * @author fengshuonan
 * @Date 2018/12/24 22:43
 */
@RequestMapping("/list") @Permission @ResponseBody public Object list(@RequestParam(required=false) String name,@RequestParam(required=false) String timeLimit,@RequestParam(required=false) Long deptId){
  String beginTime="";
  String endTime="";
  if (ToolUtil.isNotEmpty(timeLimit)) {
    String[] split=timeLimit.split(" - ");
    beginTime=split[0];
    endTime=split[1];
  }
  if (ShiroKit.isAdmin()) {
    Page<Map<String,Object>> users=userService.selectUsers(null,name,beginTime,endTime,deptId);
    Page wrapped=new UserWrapper(users).wrap();
    return LayuiPageFactory.createPageInfo(wrapped);
  }
 else {
    DataScope dataScope=new DataScope(ShiroKit.getDeptDataScope());
    Page<Map<String,Object>> users=userService.selectUsers(dataScope,name,beginTime,endTime,deptId);
    Page wrapped=new UserWrapper(users).wrap();
    return LayuiPageFactory.createPageInfo(wrapped);
  }
}
