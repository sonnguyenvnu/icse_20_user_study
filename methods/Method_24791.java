static private boolean isMissingBraceProblem(IProblem iproblem){
  if (iproblem.getID() == IProblem.ParsingErrorInsertToComplete) {
    char brace=iproblem.getArguments()[0].charAt(0);
    return brace == '{' || brace == '}';
  }
 else   if (iproblem.getID() == IProblem.ParsingErrorInsertTokenAfter) {
    char brace=iproblem.getArguments()[1].charAt(0);
    return brace == '{' || brace == '}';
  }
  return false;
}
