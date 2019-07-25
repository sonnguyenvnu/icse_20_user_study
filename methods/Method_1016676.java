private String lenformatted(String s,int l){
  l=l - s.length();
  while (l > 0) {
    s=" " + s;
    l--;
  }
  return s;
}
