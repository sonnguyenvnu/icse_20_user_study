@NonNull public static ArrayList<String> languages(){
  ArrayList<String> lang=new ArrayList<>();
  lang.addAll(Stream.of(colors).filter(value -> value != null && !InputHelper.isEmpty(value.getKey())).map(Map.Entry::getKey).collect(Collectors.toCollection(ArrayList::new)));
  lang.add(0,"All Languages");
  lang.addAll(1,POPULAR_LANG);
  return lang;
}
