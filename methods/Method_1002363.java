@RequestMapping(value="/org/bytesoft/bytetcc/commit/{xid}/{opc}",method=RequestMethod.POST) @ResponseBody public void commit(@PathVariable("xid") String identifier,@PathVariable("opc") boolean onePhase,HttpServletResponse response){
  try {
    XidFactory xidFactory=this.beanFactory.getCompensableXidFactory();
    byte[] byteArray=ByteUtils.stringToByteArray(identifier);
    Xid xid=xidFactory.createGlobalXid(byteArray);
    this.compensableCoordinator.commit(xid,onePhase);
  }
 catch (  XAException ex) {
    logger.error("Error occurred while committing transaction: {}.",identifier,ex);
    response.addHeader("failure","true");
    response.addHeader("XA_XAER",String.valueOf(ex.errorCode));
    response.setStatus(500);
  }
catch (  RuntimeException ex) {
    logger.error("Error occurred while committing transaction: {}.",identifier,ex);
    response.addHeader("failure","true");
    response.setStatus(500);
  }
}
