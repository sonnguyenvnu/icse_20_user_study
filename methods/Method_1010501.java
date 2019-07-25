public int next(){
  tokenOffset=currOffset;
  name=null;
  if (lastTag == OPEN_TAG) {
    currDepth++;
  }
  lastTag=-1;
  if (chr == '<') {
    shift();
    skipSpaces();
    if (Character.isJavaIdentifierStart(chr)) {
      skipName();
      while (chr != 0 && chr != '>') {
        shift();
      }
      if (chr == '>') {
        int slashIndex=currOffset - 1;
        shift();
        while (slashIndex > tokenOffset && Character.isWhitespace(data[slashIndex])) {
          slashIndex--;
        }
        return lastTag=data[slashIndex] == '/' ? SIMPLE_TAG : OPEN_TAG;
      }
      return OTHER;
    }
 else     if (chr == '/') {
      shift();
      skipSpaces();
      if (Character.isJavaIdentifierStart(chr)) {
        skipName();
        while (chr != 0 && chr != '>') {
          shift();
        }
        if (chr == '>') {
          shift();
          currDepth--;
          return lastTag=CLOSE_TAG;
        }
      }
      return OTHER;
    }
  }
  if (chr != 0) {
    while (chr != 0 && chr != '<') {
      shift();
    }
    return OTHER;
  }
  return EOI;
}
