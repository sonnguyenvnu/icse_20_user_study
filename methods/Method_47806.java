@NonNull private List<T> cursorToMultipleRecords(Cursor c){
  List<T> records=new LinkedList<>();
  while (c.moveToNext())   records.add(cursorToSingleRecord(c));
  return records;
}
