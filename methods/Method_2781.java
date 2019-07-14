@Override public String getValue(){
  StringBuilder sb=new StringBuilder();
  for (  Word word : innerList) {
    sb.append(word.value);
  }
  return sb.toString();
}
