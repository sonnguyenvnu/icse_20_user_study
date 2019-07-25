@RequestMapping(method={RequestMethod.GET}) public Object invoke(){
  try {
    if (cachedObject == null) {
      cachedObject=Analyzer.getAllPomInfo();
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  return cachedObject;
}
