public static Tag NEW(String name){
  Tag tag=new Tag();
  tag.set(new HtmlToken().setName(name));
  return tag;
}
