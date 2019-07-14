public CharSequence substring(CharSequence source,int start,int end){
  if (source instanceof SpannableStringBuilder) {
    return source.subSequence(start,end);
  }
 else   if (source instanceof SpannedString) {
    return source.subSequence(start,end);
  }
 else {
    return TextUtils.substring(source,start,end);
  }
}
