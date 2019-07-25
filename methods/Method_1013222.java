private final String[] comments(Token t){
  Token nextPre=nullToken;
  int cPre=0;
  if (t.specialToken == null) {
    return ns;
  }
  ;
  Token tmp_t=t.specialToken;
  while (tmp_t != null) {
    cPre++;
    tmp_t.next=nextPre;
    nextPre=tmp_t;
    tmp_t=tmp_t.specialToken;
  }
  ;
  String[] aPre=new String[cPre];
  tmp_t=nextPre;
  cPre=0;
  while (tmp_t != nullToken) {
    aPre[cPre]=tmp_t.image;
    cPre++;
    tmp_t=tmp_t.next;
  }
  ;
  return aPre;
}
