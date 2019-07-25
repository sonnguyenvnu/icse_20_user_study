/** 
 * ??
 */
@Override public R<Boolean> delete(String id){
  String input=id.replace(",","");
  return new R<>(baseService.removeById(Long.valueOf(input)));
}
