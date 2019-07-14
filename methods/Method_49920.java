private static boolean isDuplicateMessageExtra(Cursor cursor,RetrieveConf rc){
  EncodedStringValue encodedSubjectReceived=null;
  EncodedStringValue encodedSubjectStored=null;
  String subjectReceived=null;
  String subjectStored=null;
  String subject=null;
  encodedSubjectReceived=rc.getSubject();
  if (encodedSubjectReceived != null) {
    subjectReceived=encodedSubjectReceived.getString();
  }
  for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
    int subjectIdx=cursor.getColumnIndex(Mms.SUBJECT);
    int charsetIdx=cursor.getColumnIndex(Mms.SUBJECT_CHARSET);
    subject=cursor.getString(subjectIdx);
    int charset=cursor.getInt(charsetIdx);
    if (subject != null) {
      encodedSubjectStored=new EncodedStringValue(charset,PduPersister.getBytes(subject));
    }
    if (encodedSubjectStored == null && encodedSubjectReceived == null) {
      return true;
    }
 else     if (encodedSubjectStored != null && encodedSubjectReceived != null) {
      subjectStored=encodedSubjectStored.getString();
      if (!TextUtils.isEmpty(subjectStored) && !TextUtils.isEmpty(subjectReceived)) {
        return subjectStored.equals(subjectReceived);
      }
 else       if (TextUtils.isEmpty(subjectStored) && TextUtils.isEmpty(subjectReceived)) {
        return true;
      }
    }
  }
  return false;
}
