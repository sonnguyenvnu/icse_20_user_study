@Override protected void onDetach(@NonNull View view){
  InputMethodManager imm=(InputMethodManager)editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
  imm.hideSoftInputFromWindow(editText.getWindowToken(),0);
}
