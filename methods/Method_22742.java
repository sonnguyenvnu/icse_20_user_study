public int findMatchingBracket(final String text,final int pos){
  if (pos < 0 || pos >= text.length())   return -1;
  final char alpha=text.charAt(pos);
  final char beta;
  final int direction;
switch (alpha) {
case '(':
    beta=')';
  direction=1;
break;
case ')':
beta='(';
direction=-1;
break;
case '[':
beta=']';
direction=1;
break;
case ']':
beta='[';
direction=-1;
break;
case '{':
beta='}';
direction=1;
break;
case '}':
beta='{';
direction=-1;
break;
default :
return -1;
}
if (offsets == null) parse(text);
int p;
for (p=0; p < offsets.size(); p++) if (offsets.get(p) == pos) break;
if (p == offsets.size()) {
return -1;
}
int depth=1;
for (p+=direction; p >= 0 && p < offsets.size(); p+=direction) {
final int offset=offsets.get(p);
final char c=text.charAt(offset);
if (c == alpha) depth++;
 else if (c == beta) depth--;
if (depth == 0) return offset;
}
return -1;
}
