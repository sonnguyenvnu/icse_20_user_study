/** 
 * ??????????
 * @author zhangzhiwei
 */
public void auth(){
  renderJson(R.ok().put("data",adminRoleService.auth(BaseUtil.getUser().getUserId())));
}
