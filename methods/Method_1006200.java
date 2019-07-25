@Override public String format(String fieldText){
  if (fieldText == null) {
    return "";
  }
  StringBuilder sb=new StringBuilder();
  AuthorList al=AuthorList.parse(fieldText);
  if ((maxAuthors < 0) || (al.getNumberOfAuthors() <= maxAuthors)) {
    for (int i=0; i < al.getNumberOfAuthors(); i++) {
      Author a=al.getAuthor(i);
      addSingleName(sb,a,(flMode == Authors.FIRST_FIRST) || ((flMode == Authors.LF_FF) && (i > 0)));
      if (i < (al.getNumberOfAuthors() - 2)) {
        sb.append(separator);
      }
 else       if (i < (al.getNumberOfAuthors() - 1)) {
        sb.append(lastSeparator);
      }
    }
  }
 else {
    for (int i=0; i < Math.min(al.getNumberOfAuthors() - 1,authorNumberEtAl); i++) {
      if (i > 0) {
        sb.append(separator);
      }
      addSingleName(sb,al.getAuthor(i),flMode == Authors.FIRST_FIRST);
    }
    sb.append(etAlString);
  }
  return sb.toString();
}
