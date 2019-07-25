/** 
 * Converts the RTF text read from this converter's <code>Reader</code> into plain text.  It is the caller's responsibility to close the reader after this method is called.
 * @return The plain text.
 * @throws IOException If an IO error occurs.
 */
private String convert() throws IOException {
  int i=r.read();
  if (i != '{') {
    throw new IOException("Invalid RTF file");
  }
  while ((i=r.read()) != -1) {
    char ch=(char)i;
switch (ch) {
case '{':
      if (inControlWord && controlWord.length() == 0) {
        sb.append('{');
        controlWord.setLength(0);
        inControlWord=false;
      }
 else {
        blockCount++;
      }
    break;
case '}':
  if (inControlWord && controlWord.length() == 0) {
    sb.append('}');
    controlWord.setLength(0);
    inControlWord=false;
  }
 else {
    blockCount--;
  }
break;
case '\\':
if (blockCount == 0) {
if (inControlWord) {
  if (controlWord.length() == 0) {
    sb.append('\\');
    controlWord.setLength(0);
    inControlWord=false;
  }
 else {
    endControlWord();
    inControlWord=true;
  }
}
 else {
  inControlWord=true;
}
}
break;
case ' ':
if (blockCount == 0) {
if (inControlWord) {
endControlWord();
}
 else {
sb.append(' ');
}
}
break;
case '\r':
case '\n':
if (blockCount == 0) {
if (inControlWord) {
endControlWord();
}
}
break;
default :
if (blockCount == 0) {
if (inControlWord) {
controlWord.append(ch);
}
 else {
sb.append(ch);
}
}
break;
}
}
return sb.toString();
}
