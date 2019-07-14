@Override public Object getField(String name,Properties bindings){
  if ("id".equals(name)) {
    return id;
  }
 else   if ("best".equals(name)) {
    return candidates != null && candidates.size() > 0 ? candidates.get(0) : null;
  }
 else   if ("candidates".equals(name)) {
    return candidates;
  }
 else   if ("judgment".equals(name) || "judgement".equals(name)) {
    return judgmentToString();
  }
 else   if ("judgmentAction".equals(name) || "judgementAction".equals(name)) {
    return judgmentAction;
  }
 else   if ("judgmentHistoryEntry".equals(name) || "judgementHistoryEntry".equals(name)) {
    return judgmentHistoryEntry;
  }
 else   if ("judgmentBatchSize".equals(name) || "judgementBatchSize".equals(name)) {
    return judgmentBatchSize;
  }
 else   if ("matched".equals(name)) {
    return judgment == Judgment.Matched;
  }
 else   if ("new".equals(name)) {
    return judgment == Judgment.New;
  }
 else   if ("match".equals(name)) {
    return match;
  }
 else   if ("matchRank".equals(name)) {
    return matchRank;
  }
 else   if ("features".equals(name)) {
    return new Features();
  }
 else   if ("service".equals(name)) {
    return service;
  }
 else   if ("identifierSpace".equals(name)) {
    return identifierSpace;
  }
 else   if ("schemaSpace".equals(name)) {
    return schemaSpace;
  }
  return null;
}
