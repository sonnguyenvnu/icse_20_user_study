private static void showToast(@NonNull CharSequence copiedText,@NonNull Context context){
  boolean ellipsized=false;
  if (copiedText.length() > TOAST_COPIED_TEXT_MAX_LENGTH) {
    copiedText=copiedText.subSequence(0,TOAST_COPIED_TEXT_MAX_LENGTH);
    ellipsized=true;
  }
  int indexOfFirstNewline=TextUtils.indexOf(copiedText,'\n');
  if (indexOfFirstNewline != -1) {
    int indexOfSecondNewline=TextUtils.indexOf(copiedText,'\n',indexOfFirstNewline + 1);
    if (indexOfSecondNewline != -1) {
      copiedText=copiedText.subSequence(0,indexOfSecondNewline);
      ellipsized=true;
    }
  }
  if (ellipsized) {
    copiedText=copiedText.toString() + '\u2026';
  }
  ToastUtils.show(context.getString(R.string.copied_to_clipboard_format,copiedText),context);
}
