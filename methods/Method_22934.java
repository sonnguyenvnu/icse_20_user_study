/** 
 * buffer a string for insertion at the end of the DefaultStyledDocument 
 */
public void appendString(String str,AttributeSet a){
  while (str.length() > 0) {
    if (needLineBreak || currentLineLength > maxLineLength) {
      elements.add(new ElementSpec(a,ElementSpec.EndTagType));
      elements.add(new ElementSpec(a,ElementSpec.StartTagType));
      currentLineLength=0;
    }
    if (str.indexOf('\n') == -1) {
      elements.add(new ElementSpec(a,ElementSpec.ContentType,str.toCharArray(),0,str.length()));
      currentLineLength+=str.length();
      needLineBreak=false;
      str=str.substring(str.length());
    }
 else {
      elements.add(new ElementSpec(a,ElementSpec.ContentType,str.toCharArray(),0,str.indexOf('\n') + 1));
      needLineBreak=true;
      str=str.substring(str.indexOf('\n') + 1);
    }
  }
  if (elements.size() > 1000) {
    insertAll();
  }
}
