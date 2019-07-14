/** 
 * Handle open and empty ID attribute tags.
 */
protected void onIdAttrStart(final Tag tag){
  String id=tag.getId().toString().substring(7);
  String tagName;
  String idName;
  int dashIndex=id.indexOf('-');
  if (dashIndex == -1) {
    tagName=id;
    idName=null;
  }
 else {
    tagName=id.substring(0,dashIndex);
    idName=id.substring(dashIndex + 1);
  }
  if (tag.getType() == TagType.SELF_CLOSING) {
    checkNestedDecoraTags();
    decoraTagName=tagName;
    decoraIdName=idName;
    decoraTagStart=tag.getTagPosition();
    decoraTagEnd=tag.getTagPosition() + tag.getTagLength();
    defineDecoraTag();
    return;
  }
  if (tag.getType() == TagType.START) {
    checkNestedDecoraTags();
    decoraTagName=tagName;
    decoraIdName=idName;
    decoraTagStart=tag.getTagPosition();
    decoraTagDefaultValueStart=tag.getTagPosition() + tag.getTagLength();
    closingTagName=tag.getName().toString();
    closingTagDeepLevel=tag.getDeepLevel();
  }
}
