@NonNull public static List<String> listThemes(){
  try {
    List<String> list=Stream.of(App.getInstance().getAssets().list("highlight/styles/themes")).map(s -> "themes/" + s).toList();
    list.add(0,"prettify.css");
    list.add(1,"prettify_dark.css");
    return list;
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return Collections.emptyList();
}
