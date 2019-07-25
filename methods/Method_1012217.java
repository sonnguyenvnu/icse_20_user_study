/** 
 * ??????????????
 * @param context
 * @param data
 * @return
 */
public static XUISimpleAdapter create(Context context,List<String> data){
  if (data != null && data.size() > 0) {
    List<AdapterItem> lists=new ArrayList<>();
    for (int i=0; i < data.size(); i++) {
      lists.add(new AdapterItem(data.get(i)));
    }
    return new XUISimpleAdapter(context,lists);
  }
 else {
    return new XUISimpleAdapter(context);
  }
}
