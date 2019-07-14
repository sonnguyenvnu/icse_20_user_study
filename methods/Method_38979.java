@Override public void text(final CharSequence text){
  try {
    appendable.append(HtmlEncoder.text(text));
  }
 catch (  IOException ioex) {
    throw new LagartoException(ioex);
  }
}
