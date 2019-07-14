private void processInputStartTag(final Tag tag){
  CharSequence tagType=tag.getAttributeValue(TYPE);
  if (tagType == null) {
    return;
  }
  CharSequence nameSequence=tag.getAttributeValue(NAME);
  if (nameSequence == null) {
    return;
  }
  String name=nameSequence.toString();
  Object valueObject=resolver.value(name);
  if (valueObject == null) {
    return;
  }
  String tagTypeName=tagType.toString().toLowerCase();
  if (tagTypeName.equals(TEXT) || tagTypeName.equals(HIDDEN) || tagTypeName.equals(IMAGE) || tagTypeName.equals(PASSWORD)) {
    String value=valueToString(name,valueObject);
    if (value == null) {
      return;
    }
    tag.setAttribute(VALUE,value);
  }
 else   if (tagTypeName.equals(CHECKBOX)) {
    CharSequence tagValue=tag.getAttributeValue(VALUE);
    if (tagValue == null) {
      tagValue=TRUE;
    }
    tagValue=tagValue.toString();
    if (valueObject.getClass().isArray()) {
      String[] vs=StringUtil.toStringArray(valueObject);
      for (      String vsk : vs) {
        if ((vsk != null) && (vsk.contentEquals(tagValue))) {
          tag.setAttribute(CHECKED,null);
        }
      }
    }
 else     if (tagValue.equals(valueObject.toString())) {
      tag.setAttribute(CHECKED,null);
    }
  }
 else   if (tagType.equals(RADIO)) {
    CharSequence tagValue=tag.getAttributeValue(VALUE);
    if (tagValue != null) {
      tagValue=tagValue.toString();
      if (tagValue.equals(valueObject.toString())) {
        tag.setAttribute(CHECKED,null);
      }
    }
  }
}
