static char[] unsafeGetChars(final String string){
  final char[] value=(char[])UNSAFE.getObject(string,STRING_VALUE_FIELD_OFFSET);
  if (STRING_OFFSET_FIELD_OFFSET != -1) {
    final int offset=UNSAFE.getInt(string,STRING_OFFSET_FIELD_OFFSET);
    final int count=UNSAFE.getInt(string,STRING_COUNT_FIELD_OFFSET);
    if (offset == 0 && count == value.length) {
      return value;
    }
 else {
      final char[] result=new char[count];
      System.arraycopy(value,offset,result,0,count);
      return result;
    }
  }
 else {
    return value;
  }
}
