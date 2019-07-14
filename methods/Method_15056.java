/** 
 * ??id?????????setOnClickListener
 * @param id
 * @param l
 * @return
 */
@SuppressWarnings("unchecked") public <V extends View>V findViewById(int id,OnClickListener l){
  V v=(V)findViewById(id);
  v.setOnClickListener(l);
  return v;
}
