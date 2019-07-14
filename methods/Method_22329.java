@NonNull private static String[] jsonArrayToList(@Nullable JSONArray array){
  final List<String> list=new ArrayList<>();
  if (array != null) {
    final int length=array.length();
    for (int i=0; i < length; i++) {
      list.add(String.valueOf(array.opt(i)));
    }
  }
  return list.toArray(new String[list.size()]);
}
