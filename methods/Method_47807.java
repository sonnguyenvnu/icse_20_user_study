@NonNull private T cursorToSingleRecord(Cursor cursor){
  try {
    Constructor constructor=klass.getDeclaredConstructors()[0];
    constructor.setAccessible(true);
    T record=(T)constructor.newInstance();
    int index=0;
    for (    Field field : getFields())     copyFieldFromCursor(record,field,cursor,index++);
    return record;
  }
 catch (  Exception e) {
    throw new RuntimeException(e);
  }
}
