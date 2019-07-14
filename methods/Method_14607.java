@Override protected String getBriefDescription(Project project){
  if (_judgment == Judgment.None) {
    return "Discard recon judgments for cells containing \"" + _similarValue + "\" in column " + _columnName;
  }
 else   if (_judgment == Judgment.New) {
    if (_shareNewTopics) {
      return "Mark to create one single new item for all cells containing \"" + _similarValue + "\" in column " + _columnName;
    }
 else {
      return "Mark to create one new item for each cell containing \"" + _similarValue + "\" in column " + _columnName;
    }
  }
 else   if (_judgment == Judgment.Matched) {
    return "Match item " + _match.name + " (" + _match.id + ") for cells containing \"" + _similarValue + "\" in column " + _columnName;
  }
  throw new InternalError("Can't get here");
}
