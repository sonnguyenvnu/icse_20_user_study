/** 
 * @param closingTagName
 * @return true if a matching tag was found. False if no tag with this namewas ever opened ( or registered )
 */
public boolean closeTag(String closingTagName){
  if (StringUtil.isEmpty(closingTagName)) {
    throw new IllegalStateException("Tried to close a tag with empty name");
  }
  int lastRegisteredTagIdx=tagList.size() - 1;
  boolean matchingTagFound=false;
  List<ASTElement> processedElmnts=new ArrayList<>();
  for (int i=lastRegisteredTagIdx; i >= 0; i--) {
    ASTElement parent=tagList.get(i);
    String parentName=parent.getName();
    processedElmnts.add(parent);
    if (parentName.equals(closingTagName)) {
      parent.setUnclosed(false);
      parent.setEmpty(false);
      matchingTagFound=true;
      break;
    }
 else {
      if (!parent.isEmpty()) {
        parent.setUnclosed(true);
      }
      parent.setEmpty(true);
    }
  }
  if (matchingTagFound) {
    tagList.removeAll(processedElmnts);
  }
  return matchingTagFound;
}
