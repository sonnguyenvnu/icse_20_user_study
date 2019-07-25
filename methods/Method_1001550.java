public static Command create(FontPosition position){
  return new CommandCreoleExposantChange("^(?i)(" + "\\<" + position.getHtmlTag() + "\\>" + "(.*?)\\</" + position.getHtmlTag() + "\\>)",position);
}
