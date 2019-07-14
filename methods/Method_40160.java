private List<Style> stylesForFile(String path){
  List<Style> styles=fileStyles.get(path);
  if (styles == null) {
    styles=new ArrayList<>();
    fileStyles.put(path,styles);
  }
  return styles;
}
