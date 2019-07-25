@Override public boolean contains(BibEntry entry){
  Set<String> content=getFieldContentAsWords(entry);
  if (caseSensitive) {
    return content.containsAll(searchWords);
  }
 else {
    return containsCaseInsensitive(content,searchWords);
  }
}
