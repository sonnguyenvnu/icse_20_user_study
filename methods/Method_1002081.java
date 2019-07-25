/** 
 * ????
 * @author stylefeng
 * @Date 2019-03-13
 */
@ResponseBody @RequestMapping("/list") public LayuiPageInfo list(DictParam dictParam){
  return this.dictService.findPageBySpec(dictParam);
}
