public static void writeConditionalComment(final Appendable appendable,final CharSequence value,final boolean isStartingTag,final boolean downlevelHidden,final boolean isHiddenEndTag) throws IOException {
  if (isStartingTag) {
    if (downlevelHidden) {
      appendable.append("<!--[");
    }
 else {
      appendable.append("<![");
    }
    appendable.append(value);
    appendable.append("]>");
  }
 else {
    if (isHiddenEndTag) {
      appendable.append("<!--");
    }
    appendable.append("<![");
    appendable.append(value);
    if (downlevelHidden) {
      appendable.append("]-->");
    }
 else {
      appendable.append("]>");
    }
  }
}
