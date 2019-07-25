@RequestMapping(value="/org/bytesoft/bytetcc/prepare/{xid}",method=RequestMethod.POST) @ResponseBody public int prepare(@PathVariable("xid") String identifier,HttpServletResponse response){
  try {
    XidFactory xidFactory=this.beanFactory.getCompensableXidFactory();
    byte[] byteArray=ByteUtils.stringToByteArray(identifier);
    Xid xid=xidFactory.createGlobalXid(byteArray);
    return this.compensableCoordinator.prepare(xid);
  }
 catch (  XAException ex) {
    logger.error("Error occurred while preparing transaction: {}.",identifier,ex);
    response.addHeader("failure","true");
    response.addHeader("XA_XAER",String.valueOf(ex.errorCode));
    response.setStatus(500);
    return -1;
  }
catch (  RuntimeException ex) {
    logger.error("Error occurred while preparing transaction: {}.",identifier,ex);
    response.addHeader("failure","true");
    response.setStatus(500);
    return -1;
  }
}
