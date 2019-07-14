private TaskType guessModelType(TagSet tagSet){
  if (tagSet.size() == 4 && tagSet.idOf("B") != -1 && tagSet.idOf("M") != -1 && tagSet.idOf("E") != -1 && tagSet.idOf("S") != -1) {
    return TaskType.CWS;
  }
  if (tagSet.idOf("O") != -1) {
    for (    String tag : tagSet.tags()) {
      String[] parts=tag.split("-");
      if (parts.length > 1) {
        if (parts[0].length() == 1 && "BMES".contains(parts[0]))         return TaskType.NER;
      }
    }
  }
  return TaskType.POS;
}
