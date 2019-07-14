private List<StyleRun> stylesForFile(String path){
  List<StyleRun> styles=fileStyles.get(path);
  if (styles == null) {
    styles=new ArrayList<StyleRun>();
    fileStyles.put(path,styles);
  }
  return styles;
}
