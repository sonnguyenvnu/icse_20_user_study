/** 
 * ??List<String>?????????? ?????????“this.list = list;”?? ???constructor???adapter??ModleAdapter(Context context, List<Object> list)???constructor???
 * @param list
 * @return
 */
@SuppressLint("UseSparseArrays") private void initList(List<Entry<String,String>> list){
  this.list=list;
  if (hasCheck) {
    hashMap=new HashMap<Integer,Boolean>();
    if (list != null) {
      for (int i=0; i < list.size(); i++) {
        hashMap.put(i,false);
      }
    }
  }
}
