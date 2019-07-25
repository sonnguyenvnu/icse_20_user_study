/** 
 * ??????---???
 */
@RequestMapping(params="regionSelect",method=RequestMethod.GET) @ResponseBody public List<Map<String,String>> cityselect(HttpServletRequest req) throws Exception {
  logger.info("----?????-----");
  String pid=req.getParameter("pid");
  List<Map<String,String>> list=jeecgMinidaoDao.getProCity(pid);
  return jeecgMinidaoDao.getProCity(pid);
}
