/** 
 */
@RequestMapping(value="/getPayType",method=RequestMethod.GET) @ResponseBody public List getPayType(@RequestParam("payWayCode") String payWayCode){
  return PayTypeEnum.getWayList(payWayCode);
}
