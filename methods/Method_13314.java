public int searchEndIndexOfValue(String text,int startLineIndex,int startIndex){
  int length=text.length();
  int index=startIndex;
  while (index < length) {
switch (text.charAt(index)) {
case '\r':
      if ((index - startLineIndex >= 70) && (index + 1 < length) && (text.charAt(index + 1) == ' ')) {
        startLineIndex=index + 1;
      }
 else       if ((index - startLineIndex >= 70) && (index + 2 < length) && (text.charAt(index + 1) == '\n') && (text.charAt(index + 2) == ' ')) {
        index++;
        startLineIndex=index + 1;
      }
 else {
        return index;
      }
    break;
case '\n':
  if ((index - startLineIndex >= 70) && (index + 1 < length) && (text.charAt(index + 1) == ' ')) {
    startLineIndex=index + 1;
  }
 else {
    return index;
  }
break;
}
index++;
}
return index;
}
