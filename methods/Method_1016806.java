protected String[] ngramify(CharSequence s){
  if (distinguishBorders) {
    s=new StringBuilder().append(startBorderChar).append(s).append(endBorderChar).toString();
  }
  int count=s.length() - n;
  String[] ngrams=new String[count];
  for (int i=0; i < count; i++) {
    ngrams[i]=s.subSequence(i,i + n).toString();
  }
  return ngrams;
}
