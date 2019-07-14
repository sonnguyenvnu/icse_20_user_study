protected static int writeSignature(StringBuilder sb,String descriptor,int length,int index,boolean varargsFlag){
  while (true) {
    int dimensionLength=0;
    if (descriptor.charAt(index) == '[') {
      dimensionLength++;
      while (++index < length) {
        if ((descriptor.charAt(index) == 'L') && (index + 1 < length) && (descriptor.charAt(index + 1) == '[')) {
          index++;
          length--;
          dimensionLength++;
        }
 else         if (descriptor.charAt(index) == '[') {
          dimensionLength++;
        }
 else {
          break;
        }
      }
    }
switch (descriptor.charAt(index)) {
case 'B':
      sb.append("byte");
    index++;
  break;
case 'C':
sb.append("char");
index++;
break;
case 'D':
sb.append("double");
index++;
break;
case 'F':
sb.append("float");
index++;
break;
case 'I':
sb.append("int");
index++;
break;
case 'J':
sb.append("long");
index++;
break;
case 'L':
case '.':
int beginIndex=++index;
char c='.';
while (index < length) {
c=descriptor.charAt(index);
if ((c == ';') || (c == '<')) break;
index++;
}
String internalClassName=descriptor.substring(beginIndex,index);
int lastPackageSeparatorIndex=internalClassName.lastIndexOf('/');
if (lastPackageSeparatorIndex >= 0) {
internalClassName=internalClassName.substring(lastPackageSeparatorIndex + 1);
}
sb.append(internalClassName.replace('$','.'));
if (c == '<') {
sb.append('<');
index=writeSignature(sb,descriptor,length,index + 1,false);
while (descriptor.charAt(index) != '>') {
sb.append(", ");
index=writeSignature(sb,descriptor,length,index,false);
}
sb.append('>');
index++;
}
if (descriptor.charAt(index) == ';') index++;
break;
case 'S':
sb.append("short");
index++;
break;
case 'T':
beginIndex=++index;
index=descriptor.substring(beginIndex,length).indexOf(';');
sb.append(descriptor.substring(beginIndex,index));
index++;
break;
case 'V':
sb.append("void");
index++;
break;
case 'Z':
sb.append("boolean");
index++;
break;
case '-':
sb.append("? super ");
index=writeSignature(sb,descriptor,length,index + 1,false);
break;
case '+':
sb.append("? extends ");
index=writeSignature(sb,descriptor,length,index + 1,false);
break;
case '*':
sb.append('?');
index++;
break;
case 'X':
case 'Y':
sb.append("int");
index++;
break;
default :
throw new RuntimeException("SignatureWriter.WriteSignature: invalid signature '" + descriptor + "'");
}
if (varargsFlag) {
if (dimensionLength > 0) {
while (--dimensionLength > 0) sb.append("[]");
sb.append("...");
}
}
 else {
while (dimensionLength-- > 0) sb.append("[]");
}
if ((index >= length) || (descriptor.charAt(index) != '.')) break;
sb.append('.');
}
return index;
}
