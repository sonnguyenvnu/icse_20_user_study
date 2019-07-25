static void backspace(@NonNull final EditText editText){
  final KeyEvent event=new KeyEvent(0,0,0,KeyEvent.KEYCODE_DEL,0,0,0,0,KeyEvent.KEYCODE_ENDCALL);
  editText.dispatchKeyEvent(event);
}
