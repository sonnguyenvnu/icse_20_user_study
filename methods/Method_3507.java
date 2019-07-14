@Override protected List<Term> roughSegSentence(char[] sentence){
  if (sentence.length == 0)   return Collections.emptyList();
  char[] sentenceConverted=CharTable.convert(sentence);
  Table table=new Table();
  table.v=atomSegmentToTable(sentenceConverted);
  crfModel.tag(table);
  List<Term> termList=new LinkedList<Term>();
  if (HanLP.Config.DEBUG) {
    System.out.println("CRF????");
    System.out.println(table);
  }
  int offset=0;
  OUTER:   for (int i=0; i < table.v.length; offset+=table.v[i][1].length(), ++i) {
    String[] line=table.v[i];
switch (line[2].charAt(0)) {
case 'B':
{
        int begin=offset;
        while (table.v[i][2].charAt(0) != 'E') {
          offset+=table.v[i][1].length();
          ++i;
          if (i == table.v.length) {
            break;
          }
        }
        if (i == table.v.length) {
          termList.add(new Term(new String(sentence,begin,offset - begin),toDefaultNature(table.v[i][0])));
          break OUTER;
        }
 else         termList.add(new Term(new String(sentence,begin,offset - begin + table.v[i][1].length()),toDefaultNature(table.v[i][0])));
      }
    break;
default :
{
    termList.add(new Term(new String(sentence,offset,table.v[i][1].length()),toDefaultNature(table.v[i][0])));
  }
break;
}
}
return termList;
}
