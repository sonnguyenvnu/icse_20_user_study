private void appendTo(final Appendable out){
  try {
    out.append(type.getStartString());
    out.append(name);
    if (attributesCount > 0) {
      for (int i=0; i < attributesCount; i++) {
        out.append(' ');
        out.append(attrNames[i]);
        final CharSequence value=attrValues[i];
        if (value != null) {
          out.append('=').append('"');
          out.append(HtmlEncoder.attributeDoubleQuoted(value));
          out.append('"');
        }
      }
    }
    out.append(type.getEndString());
  }
 catch (  IOException ioex) {
    throw new LagartoException(ioex);
  }
}
