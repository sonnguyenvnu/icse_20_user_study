/** 
 * ????????
 * @param editText
 */
public static void setEdType(final EditText editText){
  editText.addTextChangedListener(new TextWatcher(){
    @Override public void beforeTextChanged(    CharSequence s,    int start,    int count,    int after){
    }
    @Override public void onTextChanged(    CharSequence s,    int start,    int before,    int count){
      String editable=editText.getText().toString();
      String str=stringFilter(editable);
      if (!editable.equals(str)) {
        editText.setText(str);
        editText.setSelection(str.length());
      }
    }
    @Override public void afterTextChanged(    Editable s){
    }
  }
);
}
