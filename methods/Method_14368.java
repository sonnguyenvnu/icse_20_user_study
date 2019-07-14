/** 
 * @param project
 * @param parser
 * @param columnGroup
 * @param record
 * @throws ServletException
 */
static protected void processSubRecord(Project project,TreeReader parser,ImportColumnGroup columnGroup,ImportRecord record,int level,ImportParameters parameter) throws TreeReaderException {
  if (logger.isTraceEnabled()) {
    logger.trace("processSubRecord(Project,TreeReader,ImportColumnGroup,ImportRecord) lvl:" + level + " " + columnGroup);
  }
  if (parser.current() == Token.Ignorable) {
    return;
  }
  ImportColumnGroup thisColumnGroup=getColumnGroup(project,columnGroup,composeName(parser.getPrefix(),parser.getFieldName()));
  thisColumnGroup.nextRowIndex=Math.max(thisColumnGroup.nextRowIndex,columnGroup.nextRowIndex);
  int attributeCount=parser.getAttributeCount();
  for (int i=0; i < attributeCount; i++) {
    String text=parser.getAttributeValue(i);
    if (parameter.trimStrings) {
      text=text.trim();
    }
    if (text.length() > 0 | !parameter.storeEmptyStrings) {
      addCell(project,thisColumnGroup,record,composeName(parser.getAttributePrefix(i),parser.getAttributeLocalName(i)),text,parameter.storeEmptyStrings,parameter.guessDataType);
    }
  }
  while (parser.hasNext()) {
    Token eventType=parser.next();
    if (eventType == Token.StartEntity) {
      processSubRecord(project,parser,thisColumnGroup,record,level + 1,parameter);
    }
 else     if (eventType == Token.Value) {
      Serializable value=parser.getValue();
      String colName=parser.getFieldName();
      if (value instanceof String) {
        String text=(String)value;
        addCell(project,thisColumnGroup,record,colName,text,parameter.storeEmptyStrings,parameter.guessDataType);
      }
 else {
        addCell(project,thisColumnGroup,record,colName,value);
      }
    }
 else     if (eventType == Token.EndEntity) {
      break;
    }
 else     if (eventType == Token.Ignorable) {
      continue;
    }
 else {
      logger.info("unknown event type " + eventType);
    }
  }
  int nextRowIndex=thisColumnGroup.nextRowIndex;
  for (  ImportColumn column2 : thisColumnGroup.columns.values()) {
    nextRowIndex=Math.max(nextRowIndex,column2.nextRowIndex);
  }
  for (  ImportColumnGroup columnGroup2 : thisColumnGroup.subgroups.values()) {
    nextRowIndex=Math.max(nextRowIndex,columnGroup2.nextRowIndex);
  }
  thisColumnGroup.nextRowIndex=nextRowIndex;
}
