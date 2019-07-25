/** 
 * ??
 */
@RequestMapping("/delete") @ResponseBody @Override public R<Boolean> delete(String id){
  return new R<>(baseService.removeById(Integer.valueOf(id)));
}
