/** 
 * ???????UI????
 * @param list
 */
public void setList(AdapterCallBack<BA> callBack){
  if (adapter == null) {
    setAdapter(callBack.createAdapter());
  }
  callBack.refreshAdapter();
}
