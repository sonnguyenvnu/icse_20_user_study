private static void debug(WordXmlPictureE20 converter){
  if (converter.inline != null)   log.error(XmlUtils.marshaltoString(converter.inline,true,true,Context.jc,"foo","bar",Inline.class));
 else   log.error(XmlUtils.marshaltoString(converter.anchor,true,true,Context.jc,"foo","bar",Anchor.class));
}
