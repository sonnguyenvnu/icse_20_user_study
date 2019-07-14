static String process(String html){
  Matcher m=FIELD.matcher(html);
  List<Match> matches=new ArrayList<>(64);
  Match lastMatch=null;
  boolean foundSame=false;
  StringBuilder out=new StringBuilder(html.length());
  boolean altColor=false;
  while (m.find()) {
    if (lastMatch == null) {
      out.append(html,0,m.start());
      matches.add(lastMatch=new Match(m));
      continue;
    }
    Match match=new Match(m);
    if (match.isSameBlock(lastMatch)) {
      foundSame=true;
      matches.add(lastMatch=match);
      continue;
    }
    flush(out,matches,altColor=!altColor);
    matches.clear();
    matches.add(lastMatch=match);
  }
  if (foundSame) {
    flush(out,matches,!altColor);
    out.append(html.substring(matches.get(matches.size() - 1).end));
    return out.toString();
  }
  return html;
}
