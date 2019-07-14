public static void addPhoto(@NonNull EditText editText,@NonNull String title,@NonNull String link){
  String result="![" + InputHelper.toString(title) + "](" + InputHelper.toString(link) + ")";
  insertAtCursor(editText,result);
}
