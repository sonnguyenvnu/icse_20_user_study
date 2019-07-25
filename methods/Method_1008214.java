public boolean match(Task task){
  if (getActions() != null && getActions().length > 0 && Regex.simpleMatch(getActions(),task.getAction()) == false) {
    return false;
  }
  if (getTaskId().isSet()) {
    if (getTaskId().getId() != task.getId()) {
      return false;
    }
  }
  if (parentTaskId.isSet()) {
    if (parentTaskId.equals(task.getParentTaskId()) == false) {
      return false;
    }
  }
  return true;
}
