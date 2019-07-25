private void read(){
  currentText=null;
  currentAttribute=0;
  int tokenStart=pos, currentStart=pos;
  int ch=readChar();
  if (ch < 0) {
    eventType=END_DOCUMENT;
  }
 else   if (ch == '<') {
    currentStart=pos;
    ch=readChar();
    if (ch < 0) {
      eventType=ERROR;
    }
 else     if (ch == '?') {
      eventType=PROCESSING_INSTRUCTION;
      currentStart=pos;
      while (true) {
        ch=readChar();
        if (ch < 0) {
          error("?>");
        }
        if (ch == '?' && readChar() == '>') {
          break;
        }
      }
      if (xml.substring(currentStart).startsWith("xml")) {
        int back=tokenStart;
        read();
        tokenStart=back;
      }
 else {
        currentText=xml.substring(currentStart,pos - 1);
      }
    }
 else     if (ch == '!') {
      ch=readChar();
      if (ch == '-') {
        eventType=COMMENT;
        if (readChar() != '-') {
          error("-");
        }
        currentStart=pos;
        while (true) {
          ch=readChar();
          if (ch < 0) {
            error("-->");
          }
          if (ch == '-' && readChar() == '-') {
            read(">");
            break;
          }
        }
        currentText=xml.substring(currentStart,pos - 1);
      }
 else       if (ch == 'D') {
        read("OCTYPE");
        eventType=DTD;
        while (true) {
          ch=readChar();
          if (ch < 0) {
            break;
          }
          if (ch == '>') {
            break;
          }
        }
      }
 else       if (ch == '[') {
        read("CDATA[");
        currentStart=pos;
        eventType=CHARACTERS;
        while (true) {
          ch=readChar();
          if (ch < 0) {
            error("]]>");
          }
 else           if (ch != ']') {
            continue;
          }
          ch=readChar();
          if (ch < 0) {
            error("]]>");
          }
 else           if (ch == ']') {
            do {
              ch=readChar();
              if (ch < 0) {
                error("]]>");
              }
            }
 while (ch == ']');
            if (ch == '>') {
              currentText=xml.substring(currentStart,pos - 3);
              break;
            }
          }
        }
      }
    }
 else     if (ch == '/') {
      currentStart=pos;
      prefix=null;
      eventType=END_ELEMENT;
      while (true) {
        ch=readChar();
        if (ch < 0) {
          error(">");
        }
 else         if (ch == ':') {
          prefix=xml.substring(currentStart,pos - 1);
          currentStart=pos + 1;
        }
 else         if (ch == '>') {
          localName=xml.substring(currentStart,pos - 1);
          break;
        }
 else         if (ch <= ' ') {
          localName=xml.substring(currentStart,pos - 1);
          skipSpaces();
          read(">");
          break;
        }
      }
    }
 else {
      prefix=null;
      localName=null;
      eventType=START_ELEMENT;
      while (true) {
        ch=readChar();
        if (ch < 0) {
          error(">");
        }
 else         if (ch == ':') {
          prefix=xml.substring(currentStart,pos - 1);
          currentStart=pos + 1;
        }
 else         if (ch <= ' ') {
          localName=xml.substring(currentStart,pos - 1);
          readAttributeValues();
          ch=readChar();
        }
        if (ch == '/') {
          if (localName == null) {
            localName=xml.substring(currentStart,pos - 1);
          }
          read(">");
          endElement=true;
          break;
        }
 else         if (ch == '>') {
          if (localName == null) {
            localName=xml.substring(currentStart,pos - 1);
          }
          break;
        }
      }
    }
  }
 else {
    eventType=CHARACTERS;
    while (true) {
      ch=readChar();
      if (ch < 0) {
        break;
      }
 else       if (ch == '<') {
        back();
        break;
      }
    }
    currentText=xml.substring(currentStart,pos);
  }
  currentToken=xml.substring(tokenStart,pos);
}
