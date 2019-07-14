@RequestMapping(value="/current",method=RequestMethod.GET) public List<DataPoint> getCurrentAccountStatistics(Principal principal){
  return statisticsService.findByAccountName(principal.getName());
}
