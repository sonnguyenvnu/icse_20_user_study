/** 
 * ???????
 */
public void put(String path,String[] value){
  StringBuilder sb=new StringBuilder();
  char[] chars=path.toCharArray();
  OUT:   for (int i=0; i < chars.length; i++) {
    char c=chars[i];
switch (c) {
case '[':
case '(':
      i++;
    StringBuilder sb2=new StringBuilder();
  boolean isNumber=true;
for (; i < chars.length; i++) {
  char c2=chars[i];
switch (c2) {
case ']':
case ')':
    if ((c == '[' && c2 == ']') || (c == '(' && c2 == ')')) {
      if (isNumber && !(c == '(')) {
        sb.append(':').append(sb2);
      }
 else {
        sb.append('.').append(sb2);
      }
      continue OUT;
    }
}
isNumber=isNumber && Character.isDigit(c2);
sb2.append(c2);
}
break;
default :
sb.append(c);
break;
}
}
path=sb.toString();
putPath(path,value);
}
