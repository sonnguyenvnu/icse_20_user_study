public static Tag text(String text){
  Tag tag=new Tag();
  if (null != text) {
    text=Strings.escapeHtml(text);
  }
  tag.set(new HtmlToken().setValue(text));
  return tag;
}
