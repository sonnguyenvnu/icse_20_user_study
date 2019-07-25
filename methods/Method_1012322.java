static void input(@NonNull final EditText editText,@Nullable final Emoji emoji){
  if (emoji != null) {
    final int start=editText.getSelectionStart();
    final int end=editText.getSelectionEnd();
    if (start < 0) {
      editText.append(emoji.getUnicode());
    }
 else {
      editText.getText().replace(Math.min(start,end),Math.max(start,end),emoji.getUnicode(),0,emoji.getUnicode().length());
    }
  }
}
