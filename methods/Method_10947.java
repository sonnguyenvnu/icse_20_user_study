/** 
 * @param editText       ?????
 * @param number         ??1 -> 1 2 -> 01 3 -> 001 4 -> 0001
 * @param isStartForZero ???000??true -> ? 000 ?? false -> ? 001 ??
 */
public static void setEditNumberAuto(final EditText editText,final int number,final boolean isStartForZero){
  editText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
    @Override public void onFocusChange(    View v,    boolean hasFocus){
      if (!hasFocus) {
        setEditNumber(editText,number,isStartForZero);
      }
    }
  }
);
}
