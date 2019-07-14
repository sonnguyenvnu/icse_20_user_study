private void checkTopErrorCell(boolean init){
  if (topErrorCell == null) {
    return;
  }
  SpannableStringBuilder stringBuilder=null;
  if (fieldsErrors != null && (init || errorsValues.containsKey("error_all"))) {
    String errorText=fieldsErrors.get("error_all");
    if (errorText != null) {
      stringBuilder=new SpannableStringBuilder(errorText);
      if (init) {
        errorsValues.put("error_all","");
      }
    }
  }
  if (documentsErrors != null && (init || errorsValues.containsKey("error_document_all"))) {
    String errorText=documentsErrors.get("error_all");
    if (errorText != null) {
      if (stringBuilder == null) {
        stringBuilder=new SpannableStringBuilder(errorText);
      }
 else {
        stringBuilder.append("\n\n").append(errorText);
      }
      if (init) {
        errorsValues.put("error_document_all","");
      }
    }
  }
  if (stringBuilder != null) {
    stringBuilder.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_windowBackgroundWhiteRedText3)),0,stringBuilder.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    topErrorCell.setText(stringBuilder);
    topErrorCell.setVisibility(View.VISIBLE);
  }
 else   if (topErrorCell.getVisibility() != View.GONE) {
    topErrorCell.setVisibility(View.GONE);
  }
}
