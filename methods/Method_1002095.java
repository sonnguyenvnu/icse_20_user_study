/** 
 * ????
 * @author fengshuonan
 * @Date 2018/12/23 5:53 PM
 */
@RequestMapping(value="/view/{menuId}") @ResponseBody public ResponseData view(@PathVariable Long menuId){
  if (ToolUtil.isEmpty(menuId)) {
    throw new ServiceException(BizExceptionEnum.REQUEST_NULL);
  }
  Menu menu=this.menuService.getById(menuId);
  return ResponseData.success(menu);
}
