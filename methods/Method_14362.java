/** 
 * Seeks for recurring element in a parsed document which are likely candidates for being data records
 * @param parser The parser loaded with tree data
 * @return The path to the most numerous of the possible candidates. null if no candidates were found (less than 6 recurrences)
 */
static public String[] detectRecordElement(TreeReader parser){
  if (logger.isTraceEnabled()) {
    logger.trace("detectRecordElement(inputStream)");
  }
  List<RecordElementCandidate> candidates=new ArrayList<RecordElementCandidate>();
  try {
    while (parser.hasNext()) {
      Token eventType=parser.next();
      if (eventType == Token.StartEntity) {
        RecordElementCandidate candidate=detectRecordElement(parser,new String[]{parser.getFieldName()});
        if (candidate != null) {
          candidates.add(candidate);
        }
      }
    }
  }
 catch (  TreeReaderException e) {
    e.printStackTrace();
  }
  if (candidates.size() > 0) {
    sortRecordElementCandidates(candidates);
    return candidates.get(0).path;
  }
  logger.info("No candidate elements were found in data - at least 6 similar elements are required");
  return null;
}
