@Override public void tag(final Tag tag){
  if (!insideConditionalComment) {
    if (tag.nameEquals(T_LINK)) {
      CharSequence type=tag.getAttributeValue("type");
      if (type != null && CharSequenceUtil.equalsIgnoreCase(type,"text/css")) {
        String media=Util.toString(tag.getAttributeValue("media"));
        if (media == null || media.contains("screen")) {
          String href=Util.toString(tag.getAttributeValue("href"));
          if (cssBundleAction.acceptLink(href)) {
            String link=cssBundleAction.processLink(href);
            if (link != null) {
              tag.setAttribute("href",link);
              super.tag(tag);
            }
            return;
          }
        }
      }
    }
  }
  super.tag(tag);
}
