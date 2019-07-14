/** 
 * ????
 * @author lemon
 * @param listType
 * @return
 */
protected void getList(final int listType){
  if (hasList == false) {
    return;
  }
  list=new ArrayList<String>();
  runThread(TAG + "getList",new Runnable(){
    @Override public void run(){
      Log.i(TAG,"getList  listType = " + listType);
      if (listType == TYPE_PROFESSION) {
        list=new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.profesions)));
      }
      runUiThread(new Runnable(){
        @Override public void run(){
          dismissProgressDialog();
          if (hasList) {
            setList(list);
          }
        }
      }
);
    }
  }
);
}
