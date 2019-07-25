@RequestMapping(method=RequestMethod.GET) @ResponseBody public List<TSCompanyPositionEntity> list(){
  List<TSCompanyPositionEntity> listTSCompanyPositions=tSCompanyPositionService.getList(TSCompanyPositionEntity.class);
  return listTSCompanyPositions;
}
