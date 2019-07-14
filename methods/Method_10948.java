/** 
 * @param editText       ?????
 * @param number         ??1 -> 1 2 -> 01 3 -> 001 4 -> 0001
 * @param isStartForZero ???000??true -> ? 000 ?? false -> ? 001 ??
 */
public static void setEditNumber(EditText editText,int number,boolean isStartForZero){
  StringBuilder s=new StringBuilder(editText.getText().toString());
  StringBuilder temp=new StringBuilder();
  int i;
  for (i=s.length(); i < number; ++i) {
    s.insert(0,"0");
  }
  if (!isStartForZero) {
    for (i=0; i < number; ++i) {
      temp.append("0");
    }
    if (s.toString().equals(temp.toString())) {
      s=new StringBuilder(temp.substring(1) + "1");
    }
  }
  editText.setText(s.toString());
}
