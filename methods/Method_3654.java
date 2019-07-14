/** 
 * ??
 * @param text ??
 * @return ????
 */
public static List<Term> segment(String text){
  List<Term> termList=new LinkedList<Term>();
  Matcher matcher=WEB_URL.matcher(text);
  int begin=0;
  int end;
  while (matcher.find()) {
    end=matcher.start();
    termList.addAll(SEGMENT.seg(text.substring(begin,end)));
    termList.add(new Term(matcher.group(),Nature.xu));
    begin=matcher.end();
  }
  if (begin < text.length())   termList.addAll(SEGMENT.seg(text.substring(begin)));
  return termList;
}
