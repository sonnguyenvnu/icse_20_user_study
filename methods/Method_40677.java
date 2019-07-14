/** 
 * @return the html
 */
@NotNull public String apply(){
  buffer=new StringBuilder();
  for (  Tag tag : tags) {
    tag.insert();
  }
  if (sourceOffset < source.length()) {
    copySource(sourceOffset,source.length());
  }
  return buffer.toString();
}
