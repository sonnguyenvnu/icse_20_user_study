/** 
 * Called by handleMultilineComment.<br /> Sets jdoc_flag if at the start of a doc comment. Sends buf to result with proper indents, then clears buf.<br /> Does nothing if buf is empty.
 */
private void writeIndentedComment(){
  if (buf.length() == 0)   return;
  int firstNonSpace=0;
  while (buf.charAt(firstNonSpace) == ' ')   firstNonSpace++;
  if (lookup_com("/**"))   jdoc_flag=true;
  if (startFlag)   printIndentation();
  if (buf.charAt(firstNonSpace) == '/' && buf.charAt(firstNonSpace + 1) == '*') {
    if (startFlag && lastNonWhitespace != ';') {
      result.append(buf.substring(firstNonSpace));
    }
 else {
      result.append(buf);
    }
  }
 else {
    if (buf.charAt(firstNonSpace) == '*' || !jdoc_flag) {
      result.append(" " + buf.substring(firstNonSpace));
    }
 else {
      result.append(" * " + buf.substring(firstNonSpace));
    }
  }
  buf.setLength(0);
}
