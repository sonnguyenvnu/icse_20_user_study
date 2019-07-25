/** 
 * ??????????????ztree??
 * @author fengshuonan
 * @Date 2018/12/23 4:56 PM
 */
@RequestMapping(value="/ztree") @ResponseBody public List<ZTreeNode> ztree(@RequestParam("dictTypeId") Long dictTypeId,@RequestParam(value="dictId",required=false) Long dictId){
  return this.dictService.dictTreeList(dictTypeId,dictId);
}
