public static List<Pinyin> convertFromToneNumber(String[] pinyinArray){
  List<Pinyin> pinyinList=new ArrayList<Pinyin>(pinyinArray.length);
  for (  String py : pinyinArray) {
    pinyinList.add(convertFromToneNumber(py));
  }
  return pinyinList;
}
