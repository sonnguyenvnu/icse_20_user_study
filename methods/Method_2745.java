public static StringDictionary combine(String... args){
  String[] pathArray=args.clone();
  List<StringDictionary> dictionaryList=new LinkedList<StringDictionary>();
  for (  String path : pathArray) {
    StringDictionary dictionary=load(path);
    if (dictionary == null)     continue;
    dictionaryList.add(dictionary);
  }
  return combine(dictionaryList.toArray(new StringDictionary[0]));
}
