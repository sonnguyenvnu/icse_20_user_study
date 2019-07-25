private List<FormatterToken> format(final List<FormatterToken> argList){
  if (argList.isEmpty()) {
    return argList;
  }
  FormatterToken token=argList.get(0);
  if (token.getType() == TokenType.SPACE) {
    argList.remove(0);
    if (argList.isEmpty()) {
      return argList;
    }
  }
  token=argList.get(argList.size() - 1);
  if (token.getType() == TokenType.SPACE) {
    argList.remove(argList.size() - 1);
    if (argList.isEmpty()) {
      return argList;
    }
  }
  final KeywordCase keywordCase=formatterCfg.getKeywordCase();
  for (  FormatterToken anArgList : argList) {
    token=anArgList;
    if (token.getType() == TokenType.KEYWORD) {
      token.setString(keywordCase.transform(token.getString()));
    }
  }
  for (int index=argList.size() - 1; index >= 1; index--) {
    token=argList.get(index);
    FormatterToken prevToken=argList.get(index - 1);
    if (token.getType() == TokenType.SPACE && (prevToken.getType() == TokenType.SYMBOL || prevToken.getType() == TokenType.COMMENT)) {
      argList.remove(index);
    }
 else     if ((token.getType() == TokenType.SYMBOL || token.getType() == TokenType.COMMENT) && prevToken.getType() == TokenType.SPACE) {
      argList.remove(index - 1);
    }
 else     if (token.getType() == TokenType.SPACE) {
      token.setString(" ");
    }
  }
  for (int index=0; index < argList.size() - 2; index++) {
    FormatterToken t0=argList.get(index);
    FormatterToken t1=argList.get(index + 1);
    FormatterToken t2=argList.get(index + 2);
    String tokenString=t0.getString().toUpperCase(Locale.ENGLISH);
    String token2String=t2.getString().toUpperCase(Locale.ENGLISH);
    if (t0.getType() == TokenType.KEYWORD && t1.getType() == TokenType.SPACE && t2.getType() == TokenType.KEYWORD) {
      if (((tokenString.equals("ORDER") || tokenString.equals("GROUP") || tokenString.equals("CONNECT")) && token2String.equals("BY")) || ((tokenString.equals("START")) && token2String.equals("WITH"))) {
        t0.setString(t0.getString() + " " + t2.getString());
        argList.remove(index + 1);
        argList.remove(index + 1);
      }
    }
    if (tokenString.equals("(") && t1.getString().equals("+") && token2String.equals(")")) {
      t0.setString("(+)");
      argList.remove(index + 1);
      argList.remove(index + 1);
    }
  }
  int indent=0;
  final List<Integer> bracketIndent=new ArrayList<>();
  FormatterToken prev=new FormatterToken(TokenType.SPACE," ");
  boolean encounterBetween=false;
  for (int index=0; index < argList.size(); index++) {
    token=argList.get(index);
    String tokenString=token.getString().toUpperCase(Locale.ENGLISH);
    if (token.getType() == TokenType.SYMBOL) {
      if (tokenString.equals("(")) {
        functionBracket.add(isFunction(prev.getString()) ? Boolean.TRUE : Boolean.FALSE);
        bracketIndent.add(indent);
        indent++;
        index+=insertReturnAndIndent(argList,index + 1,indent);
      }
 else       if (tokenString.equals(")") && !bracketIndent.isEmpty() && !functionBracket.isEmpty()) {
        indent=bracketIndent.remove(bracketIndent.size() - 1);
        index+=insertReturnAndIndent(argList,index,indent);
        functionBracket.remove(functionBracket.size() - 1);
      }
 else       if (tokenString.equals(",")) {
        index+=insertReturnAndIndent(argList,index + 1,indent);
      }
 else       if (statementDelimiters.contains(tokenString)) {
        indent=0;
        index+=insertReturnAndIndent(argList,index,indent);
      }
    }
 else     if (token.getType() == TokenType.KEYWORD) {
switch (tokenString) {
case "DELETE":
case "SELECT":
case "UPDATE":
case "INSERT":
case "INTO":
case "CREATE":
case "DROP":
case "TRUNCATE":
case "TABLE":
case "CASE":
        indent++;
      index+=insertReturnAndIndent(argList,index + 1,indent);
    break;
case "FROM":
case "WHERE":
case "SET":
case "START WITH":
case "CONNECT BY":
case "ORDER BY":
case "GROUP BY":
case "HAVING":
  index+=insertReturnAndIndent(argList,index,indent - 1);
index+=insertReturnAndIndent(argList,index + 1,indent);
break;
case "LEFT":
case "RIGHT":
case "INNER":
case "OUTER":
case "JOIN":
if (isJoinStart(argList,index)) {
index+=insertReturnAndIndent(argList,index,indent - 1);
}
break;
case "VALUES":
case "END":
indent--;
index+=insertReturnAndIndent(argList,index,indent);
break;
case "OR":
case "WHEN":
case "ELSE":
index+=insertReturnAndIndent(argList,index,indent);
break;
case "ON":
index+=insertReturnAndIndent(argList,index + 1,indent);
break;
case "USING":
index+=insertReturnAndIndent(argList,index,indent + 1);
break;
case "TOP":
index+=insertReturnAndIndent(argList,index,indent);
if (argList.size() < index + 3) {
index+=insertReturnAndIndent(argList,index + 3,indent);
}
break;
case "UNION":
case "INTERSECT":
case "EXCEPT":
indent-=2;
index+=insertReturnAndIndent(argList,index,indent);
indent++;
break;
case "BETWEEN":
encounterBetween=true;
break;
case "AND":
if (!encounterBetween) {
index+=insertReturnAndIndent(argList,index,indent);
}
encounterBetween=false;
break;
default :
break;
}
}
 else if (token.getType() == TokenType.COMMENT) {
boolean isComment=false;
String[] slComments=sqlDialect.getSingleLineComments();
for (String slc : slComments) {
if (token.getString().startsWith(slc)) {
isComment=true;
break;
}
}
if (!isComment) {
Pair<String,String> mlComments=sqlDialect.getMultiLineComments();
if (token.getString().startsWith(mlComments.getFirst())) {
index+=insertReturnAndIndent(argList,index + 1,indent);
}
}
}
 else if (token.getType() == TokenType.COMMAND) {
indent=0;
if (index > 0) {
index+=insertReturnAndIndent(argList,index,0);
}
index+=insertReturnAndIndent(argList,index + 1,0);
}
 else {
if (statementDelimiters.contains(tokenString)) {
indent=0;
index+=insertReturnAndIndent(argList,index + 1,indent);
}
}
prev=token;
}
for (int index=argList.size() - 1; index >= 4; index--) {
if (index >= argList.size()) {
continue;
}
FormatterToken t0=argList.get(index);
FormatterToken t1=argList.get(index - 1);
FormatterToken t2=argList.get(index - 2);
FormatterToken t3=argList.get(index - 3);
FormatterToken t4=argList.get(index - 4);
if (t4.getString().equals("(") && t3.getString().trim().isEmpty() && t1.getString().trim().isEmpty() && t0.getString().equalsIgnoreCase(")")) {
t4.setString(t4.getString() + t2.getString() + t0.getString());
argList.remove(index);
argList.remove(index - 1);
argList.remove(index - 2);
argList.remove(index - 3);
}
}
for (int index=1; index < argList.size(); index++) {
prev=argList.get(index - 1);
token=argList.get(index);
if (prev.getType() != TokenType.SPACE && token.getType() != TokenType.SPACE && !token.getString().startsWith("(")) {
if (token.getString().equals(",") || statementDelimiters.contains(token.getString())) {
continue;
}
if (isFunction(prev.getString()) && token.getString().equals("(")) {
continue;
}
if (token.getType() == TokenType.VALUE && prev.getType() == TokenType.NAME) {
continue;
}
if (token.getType() == TokenType.SYMBOL && isEmbeddedToken(token) || prev.getType() == TokenType.SYMBOL && isEmbeddedToken(prev)) {
continue;
}
if (token.getType() == TokenType.SYMBOL && prev.getType() == TokenType.SYMBOL) {
continue;
}
argList.add(index,new FormatterToken(TokenType.SPACE," "));
}
}
return argList;
}
