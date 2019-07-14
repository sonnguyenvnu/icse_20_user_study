public static List<String> converter(List<IndexValue> i_list){
  List<String> tem=new ArrayList<String>();
  char[] start={'@'};
  int j=0;
  for (; j < i_list.size(); j++) {
    IndexValue currentIndexValue=i_list.get(j);
    char[] currentIndex=currentIndexValue.getV_index().replaceAll("[0-9]","").toCharArray();
    if (j > 0) {
      start=i_list.get(j - 1).getV_index().replaceAll("[0-9]","").toCharArray();
    }
    int deep=subtraction26(currentIndex,start);
    int k=0;
    for (; k < deep - 1; k++) {
      tem.add(null);
    }
    tem.add(currentIndexValue.getV_value());
  }
  return tem;
}
