@Override public Task from(Cursor cursor){
  return Task.newBuilder().setId(cursor.getString(cursor.getColumnIndexOrThrow(ENTRY_ID.getFieldName()))).setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE.getFieldName()))).setDescription(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION.getFieldName()))).setCompleted(cursor.getInt(cursor.getColumnIndexOrThrow(COMPLETED.getFieldName())) > 0).build();
}
