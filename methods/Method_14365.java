/** 
 * @param project
 * @param parser
 * @param recordPath
 * @param pathIndex
 * @param rootColumnGroup
 * @throws ServletException
 */
static protected void findRecord(Project project,TreeReader parser,String[] recordPath,int pathIndex,ImportColumnGroup rootColumnGroup,int limit,ImportParameters parameters) throws TreeReaderException {
  if (logger.isTraceEnabled()) {
    logger.trace("findRecord(Project, TreeReader, String[], int, ImportColumnGroup - path:" + Arrays.toString(recordPath));
  }
  if (parser.current() == Token.Ignorable) {
    logger.warn("Cannot use findRecord method for START_DOCUMENT event");
    return;
  }
  String recordPathSegment=recordPath[pathIndex];
  String localName=parser.getFieldName();
  String fullName=composeName(parser.getPrefix(),localName);
  if (recordPathSegment.equals(localName) || recordPathSegment.equals(fullName)) {
    if (pathIndex < recordPath.length - 1) {
      while (parser.hasNext() && limit != 0) {
        Token eventType=parser.next();
        if (eventType == Token.StartEntity) {
          findRecord(project,parser,recordPath,pathIndex + 1,rootColumnGroup,limit--,parameters);
        }
 else         if (eventType == Token.EndEntity) {
          break;
        }
 else         if (eventType == Token.Value) {
          if (pathIndex == recordPath.length - 2) {
            String desiredFieldName=recordPath[pathIndex + 1];
            String currentFieldName=parser.getFieldName();
            if (desiredFieldName.equals(currentFieldName)) {
              processFieldAsRecord(project,parser,rootColumnGroup,parameters);
            }
          }
        }
      }
    }
 else {
      processRecord(project,parser,rootColumnGroup,parameters);
    }
  }
 else {
    skip(parser);
  }
}
