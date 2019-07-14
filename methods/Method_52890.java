public String literal(){
  if (first != null && first.equals(last)) {
    return NodeUtils.tokenLiteral(first);
  }
  Token t=first;
  final StrBuilder sb=new StrBuilder(NodeUtils.tokenLiteral(t));
  while (t != null && !t.equals(last)) {
    t=t.next;
    sb.append(NodeUtils.tokenLiteral(t));
  }
  return sb.toString();
}
