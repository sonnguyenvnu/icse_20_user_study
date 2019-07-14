public int getType(){
  if (getEvent() != null) {
switch (getEvent()) {
case commented:
      return COMMENT;
case reviewed:
case changes_requested:
    return REVIEW;
case GROUPED:
  return GROUP;
case commit_commented:
return COMMIT_COMMENTS;
default :
return EVENT;
}
}
 else {
if (issue != null || pullRequest != null) return HEADER;
 else if (status != null) return STATUS;
return 0;
}
}
