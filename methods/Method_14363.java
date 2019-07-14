static protected RecordElementCandidate detectRecordElement(TreeReader parser,String[] path){
  if (logger.isTraceEnabled()) {
    logger.trace("detectRecordElement(TreeReader, String[])");
  }
  List<RecordElementCandidate> descendantCandidates=new ArrayList<RecordElementCandidate>();
  Map<String,Integer> immediateChildCandidateMap=new HashMap<String,Integer>();
  try {
    while (parser.hasNext()) {
      Token eventType=parser.next();
      if (eventType == Token.EndEntity) {
        break;
      }
 else       if (eventType == Token.StartEntity) {
        String tagName=parser.getFieldName();
        immediateChildCandidateMap.put(tagName,immediateChildCandidateMap.containsKey(tagName) ? immediateChildCandidateMap.get(tagName) + 1 : 1);
        String[] path2=new String[path.length + 1];
        System.arraycopy(path,0,path2,0,path.length);
        path2[path.length]=tagName;
        RecordElementCandidate c=detectRecordElement(parser,path2);
        if (c != null) {
          descendantCandidates.add(c);
        }
      }
    }
  }
 catch (  TreeReaderException e) {
    e.printStackTrace();
  }
  if (immediateChildCandidateMap.size() > 0) {
    List<RecordElementCandidate> immediateChildCandidates=new ArrayList<RecordElementCandidate>(immediateChildCandidateMap.size());
    for (    Entry<String,Integer> entry : immediateChildCandidateMap.entrySet()) {
      int count=entry.getValue();
      if (count > 1) {
        String[] path2=new String[path.length + 1];
        System.arraycopy(path,0,path2,0,path.length);
        path2[path.length]=entry.getKey();
        RecordElementCandidate candidate=new RecordElementCandidate();
        candidate.path=path2;
        candidate.count=count;
        immediateChildCandidates.add(candidate);
      }
    }
    if (immediateChildCandidates.size() > 0 && immediateChildCandidates.size() < 5) {
      sortRecordElementCandidates(immediateChildCandidates);
      RecordElementCandidate ourCandidate=immediateChildCandidates.get(0);
      if (logger.isTraceEnabled()) {
        logger.trace("ourCandidate.count : " + ourCandidate.count + "; immediateChildCandidates.size() : " + immediateChildCandidates.size());
      }
      if (ourCandidate.count / immediateChildCandidates.size() > 5) {
        return ourCandidate;
      }
      descendantCandidates.add(ourCandidate);
    }
  }
  if (descendantCandidates.size() > 0) {
    sortRecordElementCandidates(descendantCandidates);
    RecordElementCandidate candidate=descendantCandidates.get(0);
    if (candidate.count / descendantCandidates.size() > 5) {
      return candidate;
    }
  }
  return null;
}
