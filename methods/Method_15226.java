/** 
 * @param tv ???
 * @param trim et????????????
 * @param clearView ?????????
 * @param isClearViewInvisible  ??et???????clearView????gone(false)??invisible(true)
 */
public void addClearListener(final TextView tv,final int blankType,final View clearView,final boolean isClearViewInvisible){
  if (tv == null || clearView == null) {
    Log.e(TAG,"addClearListener  (tv == null || clearView == null)  >> return;");
    return;
  }
  this.tv=tv;
  this.clearView=clearView;
  if (tv.getText() != null) {
    inputedString=tv.getText().toString();
  }
  clearView.setVisibility(StringUtil.isNotEmpty(tv,false) ? View.VISIBLE : View.GONE);
  clearView.setOnClickListener(new OnClickListener(){
    @Override public void onClick(    View v){
      tv.setText("");
      tv.requestFocus();
    }
  }
);
  tv.addTextChangedListener(new TextWatcher(){
    @Override public void onTextChanged(    CharSequence s,    int start,    int before,    int count){
      if (s == null || StringUtil.isNotEmpty(s.toString(),false) == false) {
        inputedString="";
        if (isClearViewInvisible == false) {
          clearView.setVisibility(View.GONE);
        }
 else {
          clearView.setVisibility(View.INVISIBLE);
        }
      }
 else {
        inputedString="" + s.toString();
        clearView.setVisibility(View.VISIBLE);
      }
    }
    @Override public void beforeTextChanged(    CharSequence s,    int start,    int count,    int after){
    }
    @Override public void afterTextChanged(    Editable s){
    }
  }
);
}
