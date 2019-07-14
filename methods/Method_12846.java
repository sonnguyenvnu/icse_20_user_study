private List<String> converter(List<String> data){
  List<String> list=new ArrayList<String>();
  if (data != null) {
    for (    String str : data) {
      list.add(TypeUtil.formatFloat(str));
    }
  }
  return list;
}
