private void copy(CharSequence text){
  ClipboardManager cm=(ClipboardManager)mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
  cm.setText(text);
}
