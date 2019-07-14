public static List<String> toSentenceList(char[] chars,boolean shortest){
  StringBuilder sb=new StringBuilder();
  List<String> sentences=new LinkedList<String>();
  for (int i=0; i < chars.length; ++i) {
    if (sb.length() == 0 && (Character.isWhitespace(chars[i]) || chars[i] == ' ')) {
      continue;
    }
    sb.append(chars[i]);
switch (chars[i]) {
case '.':
      if (i < chars.length - 1 && chars[i + 1] > 128) {
        insertIntoList(sb,sentences);
        sb=new StringBuilder();
      }
    break;
case '…':
{
    if (i < chars.length - 1 && chars[i + 1] == '…') {
      sb.append('…');
      ++i;
      insertIntoList(sb,sentences);
      sb=new StringBuilder();
    }
  }
break;
case '?':
case ',':
case ';':
case '?':
if (!shortest) {
continue;
}
case ' ':
case '	':
case ' ':
case '?':
case '!':
case '?':
case '?':
case '?':
case '\n':
case '\r':
insertIntoList(sb,sentences);
sb=new StringBuilder();
break;
}
}
if (sb.length() > 0) {
insertIntoList(sb,sentences);
}
return sentences;
}
