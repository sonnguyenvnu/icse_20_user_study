private String clean(String input){
  return StringUtil.unifyLineBreaks(input," ").trim().replaceAll(" +"," ");
}
