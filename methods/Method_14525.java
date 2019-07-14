@Deprecated static public String judgmentToString(Judgment judgment){
  if (judgment == Judgment.Matched) {
    return "matched";
  }
 else   if (judgment == Judgment.New) {
    return "new";
  }
 else {
    return "none";
  }
}
