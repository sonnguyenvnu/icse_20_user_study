/** 
 * ????????
 * @author fengshuonan
 * @Date 2018/12/23 5:34 PM
 */
@RequestMapping("/detail/{id}") @Permission(Const.ADMIN_NAME) @ResponseBody public Object detail(@PathVariable Long id){
  OperationLog operationLog=operationLogService.getById(id);
  Map<String,Object> stringObjectMap=BeanUtil.beanToMap(operationLog);
  return super.warpObject(new LogWrapper(stringObjectMap));
}
