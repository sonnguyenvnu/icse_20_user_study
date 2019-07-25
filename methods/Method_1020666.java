/** 
 * ????
 */
@RequestMapping("/delete") @ResponseBody public R<Boolean> delete(String id){
  return R.rest(sysRoleService.removeById(Integer.valueOf(id)));
}
