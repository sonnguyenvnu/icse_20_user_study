public static boolean isFieldsAvailable(){
  if (!fieldsAvailable && mSpansField == null) {
    try {
      mSpansField=SpannableString.class.getSuperclass().getDeclaredField("mSpans");
      mSpansField.setAccessible(true);
      mSpanDataField=SpannableString.class.getSuperclass().getDeclaredField("mSpanData");
      mSpanDataField.setAccessible(true);
      mSpanCountField=SpannableString.class.getSuperclass().getDeclaredField("mSpanCount");
      mSpanCountField.setAccessible(true);
    }
 catch (    Throwable e) {
      FileLog.e(e);
    }
    fieldsAvailable=true;
  }
  return mSpansField != null;
}
