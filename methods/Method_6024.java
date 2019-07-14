private static boolean shouldEscapeCharacter(char c){
switch (c) {
case '<':
case '>':
case ':':
case '"':
case '/':
case '\\':
case '|':
case '?':
case '*':
case '%':
    return true;
default :
  return false;
}
}
