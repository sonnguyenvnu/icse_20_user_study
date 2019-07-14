static public void importTreeData(TreeReader parser,Project project,String[] recordPath,ImportColumnGroup rootColumnGroup,int limit,ImportParameters parameters){
  if (logger.isTraceEnabled()) {
    logger.trace("importTreeData(TreeReader, Project, String[], ImportColumnGroup)");
  }
  try {
    while (parser.hasNext()) {
      Token eventType=parser.next();
      if (eventType == Token.StartEntity) {
        findRecord(project,parser,recordPath,0,rootColumnGroup,limit--,parameters);
      }
    }
  }
 catch (  TreeReaderException e) {
    logger.error("Exception from XML parse",e);
  }
}
