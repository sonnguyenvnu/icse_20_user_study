@Override public ContentValues from(ContentValues contentValues,Task task){
  contentValues.put(ENTRY_ID.getFieldName(),task.id());
  contentValues.put(TITLE.getFieldName(),task.title());
  contentValues.put(DESCRIPTION.getFieldName(),task.description());
  contentValues.put(COMPLETED.getFieldName(),task.completed());
  return contentValues;
}
