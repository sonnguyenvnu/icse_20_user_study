/** 
 * ??????
 * @author stylefeng
 * @Date 2019-03-13
 */
@RequestMapping("/detail") @ResponseBody public ResponseData detail(DictTypeParam dictTypeParam){
  DictType detail=this.dictTypeService.getById(dictTypeParam.getDictTypeId());
  return ResponseData.success(detail);
}
