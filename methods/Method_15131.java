/** 
 * ??id?????????setOnClickListener
 * @param id
 * @param listener
 * @return
 */
public <V extends View>V findViewById(int id,OnClickListener listener){
  V v=findViewById(id);
  v.setOnClickListener(listener);
  if (onClickViewList == null) {
    onClickViewList=new ArrayList<View>();
  }
  onClickViewList.add(v);
  return v;
}
