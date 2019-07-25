public void parse(char c){
switch (state) {
case CODE:
case SLASH:
    statement.append(c);
switch (c) {
case '{':
    statement.deleteCharAt(statement.length() - 1);
  onBlockStart(statement.toString().trim());
statement.setLength(0);
break;
case '}':
eventHandler.onBlockEnd();
statement.setLength(0);
break;
case ';':
onStatement(statement);
statement.setLength(0);
break;
case '"':
state=State.STRING_LITERAL;
break;
case '/':
if (state == State.CODE) {
state=State.SLASH;
}
 else {
state=State.LINE_COMMENT;
statement.delete(statement.length() - 2,statement.length());
}
break;
case '*':
if (state == State.CODE) {
state=State.CODE;
}
 else {
state=State.BLOCK_COMMENT;
statement.delete(statement.length() - 2,statement.length());
}
break;
}
break;
case STRING_LITERAL:
switch (c) {
case '\\':
state=State.STRING_LITERAL_ESCAPE;
break;
case '"':
statement.append(c);
state=State.CODE;
break;
}
break;
case STRING_LITERAL_ESCAPE:
statement.append(c);
state=State.STRING_LITERAL;
break;
case CHAR_LITERAL:
switch (c) {
case '\\':
state=State.CHAR_LITERAL_ESCAPE;
break;
case '\'':
statement.append(c);
state=State.CODE;
break;
}
break;
case CHAR_LITERAL_ESCAPE:
state=State.CHAR_LITERAL;
break;
case LINE_COMMENT:
if (c == '\n') {
state=State.CODE;
}
break;
case BLOCK_COMMENT:
case BLOCK_COMMENT_STAR:
switch (c) {
case '*':
state=State.BLOCK_COMMENT_STAR;
break;
case '/':
if (state == State.BLOCK_COMMENT_STAR) {
state=State.CODE;
}
break;
default :
state=State.BLOCK_COMMENT;
break;
}
break;
}
}
