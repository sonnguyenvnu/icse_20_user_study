private void processOptionOpenTag(final Tag tag){
  CharSequence tagValue=tag.getAttributeValue(VALUE);
  if (tagValue == null) {
    return;
  }
  Object vals=resolver.value(currentSelectName);
  if (vals == null) {
    return;
  }
  tagValue=tagValue.toString();
  if (vals.getClass().isArray()) {
    String[] vs=StringUtil.toStringArray(vals);
    for (    String vsk : vs) {
      if ((vsk != null) && (vsk.contentEquals(tagValue))) {
        tag.setAttribute(SELECTED,null);
      }
    }
  }
 else {
    String value=StringUtil.toString(vals);
    if (value.contentEquals(tagValue)) {
      tag.setAttribute(SELECTED,null);
    }
  }
}
