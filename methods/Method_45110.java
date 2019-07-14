private Object toStringArray(final Object content){
  if (content instanceof List) {
    @SuppressWarnings("unchecked") List<String> texts=(List<String>)content;
    return texts.toArray(new String[texts.size()]);
  }
  return content.toString();
}
