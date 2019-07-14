private boolean checkInitStatement(Token token,Info info){
  String fullAssignStatement=getFullAssignStatement(token);
  if (NEW_SCHEDULED.equals(fullAssignStatement) || NEW_SINGLE_SCHEDULED.equals(fullAssignStatement)) {
    return true;
  }
  if (fullAssignStatement.startsWith(EXECUTORS_NEW)) {
    return false;
  }
  if (!fullAssignStatement.startsWith(NEW) && !fullAssignStatement.startsWith(FULL_EXECUTORS_NEW)) {
    return true;
  }
  int index=fullAssignStatement.indexOf(BRACKETS);
  if (index == -1) {
    return true;
  }
  fullAssignStatement=fullAssignStatement.substring(0,index);
  if (info.importedExecutorsMethods.contains(fullAssignStatement)) {
    return false;
  }
  return !info.importedExecutorsMethods.contains(Executors.class.getName() + DOT + fullAssignStatement);
}
