@Override protected List<Term> roughSegSentence(char[] sentence){
  char[] tag=model.tag(sentence);
  List<Term> termList=new LinkedList<Term>();
  int offset=0;
  for (int i=0; i < tag.length; offset+=1, ++i) {
switch (tag[i]) {
case 'b':
{
        int begin=offset;
        while (tag[i] != 'e') {
          offset+=1;
          ++i;
          if (i == tag.length) {
            break;
          }
        }
        if (i == tag.length) {
          termList.add(new Term(new String(sentence,begin,offset - begin),null));
        }
 else         termList.add(new Term(new String(sentence,begin,offset - begin + 1),null));
      }
    break;
default :
{
    termList.add(new Term(new String(sentence,offset,1),null));
  }
break;
}
}
return termList;
}
