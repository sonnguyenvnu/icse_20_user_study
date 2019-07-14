private String getFullAssignStatement(final Token token){
  if (token == null) {
    return "";
  }
  StringBuilder sb=new StringBuilder(48);
  Token next=token;
  while (next.next != null && !COLON.equals(next.image)) {
    sb.append(next.image);
    next=next.next;
  }
  return sb.toString();
}
