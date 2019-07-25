private void start(){
  current=new JoinRow(new Object[filters.length]);
  Cursor cursor;
  if (batchedSubQuery) {
    assert viewTopFutureCursor != null;
    cursor=get(viewTopFutureCursor);
  }
 else {
    TableFilter f=top.filter;
    IndexCursor indexCursor=f.getIndexCursor();
    indexCursor.find(f.getSession(),f.getIndexConditions());
    cursor=indexCursor;
  }
  current.updateRow(top.id,cursor,JoinRow.S_NULL,JoinRow.S_CURSOR);
  JoinRow fake=new JoinRow(null);
  fake.next=current;
  current=fake;
}
