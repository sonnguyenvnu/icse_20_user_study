@SuppressWarnings("static-access") @RequestMapping(value={"/"},method=RequestMethod.GET) public void index(){
  long start=System.currentTimeMillis();
  try {
    logger.info("--------------------------------------------\n");
    logger.info("???????????" + arithmeticService.DoTime + "????");
    Future<Long> task=arithmeticService.subByAsync();
    arithmeticService.subByVoid();
    long sync=arithmeticService.subBySync();
    while (true) {
      if (task.isDone()) {
        long async=task.get();
        logger.info("???????????" + async + "????");
        logger.info("???????????" + sync + "????");
        break;
      }
    }
    logger.info("--------------------------------------------\n");
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
  long end=System.currentTimeMillis();
  logger.info("\t........????????" + (end - start) + "????");
}
