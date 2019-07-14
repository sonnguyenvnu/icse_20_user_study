@Override protected String createDescription(Column column,List<CellChange> cellChanges){
  if (_judgment == Judgment.None) {
    return "Discard recon judgments for " + cellChanges.size() + " cells containing \"" + _similarValue + "\" in column " + _columnName;
  }
 else   if (_judgment == Judgment.New) {
    if (_shareNewTopics) {
      return "Mark to create one single new item for " + cellChanges.size() + " cells containing \"" + _similarValue + "\" in column " + _columnName;
    }
 else {
      return "Mark to create one new item for each of " + cellChanges.size() + " cells containing \"" + _similarValue + "\" in column " + _columnName;
    }
  }
 else   if (_judgment == Judgment.Matched) {
    return "Match item " + _match.name + " (" + _match.id + ") for " + cellChanges.size() + " cells containing \"" + _similarValue + "\" in column " + _columnName;
  }
  throw new InternalError("Can't get here");
}
