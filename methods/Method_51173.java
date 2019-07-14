@Override public String toString(){
  StringBuilder buffer=new StringBuilder("(Extension is one of: ");
  for (  Language language : languages) {
    List<String> extensions=language.getExtensions();
    for (int i=0; i < extensions.size(); i++) {
      if (i > 0) {
        buffer.append(", ");
      }
      buffer.append(extensions.get(i));
    }
  }
  buffer.append(')');
  return buffer.toString();
}
