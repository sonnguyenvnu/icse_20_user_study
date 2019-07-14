/** 
 * @return the html
 */
public String apply(){
  buffer=new StringBuilder(source.length() * SOURCE_BUF_MULTIPLIER);
  for (  Tag tag : tags) {
    tag.insert();
  }
  if (sourceOffset < source.length()) {
    copySource(sourceOffset,source.length());
  }
  return buffer.toString();
}
