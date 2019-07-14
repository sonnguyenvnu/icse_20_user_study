public void writeStringWithDoubleQuote(char[] text,final char seperator){
  if (text == null) {
    writeNull();
    if (seperator != 0) {
      write(seperator);
    }
    return;
  }
  int len=text.length;
  int newcount=count + len + 2;
  if (seperator != 0) {
    newcount++;
  }
  if (newcount > buf.length) {
    if (writer != null) {
      write('"');
      for (int i=0; i < text.length; ++i) {
        char ch=text[i];
        if (isEnabled(SerializerFeature.BrowserSecure)) {
          if (ch == '(' || ch == ')' || ch == '<' || ch == '>') {
            write('\\');
            write('u');
            write(IOUtils.DIGITS[(ch >>> 12) & 15]);
            write(IOUtils.DIGITS[(ch >>> 8) & 15]);
            write(IOUtils.DIGITS[(ch >>> 4) & 15]);
            write(IOUtils.DIGITS[ch & 15]);
            continue;
          }
        }
        if (isEnabled(SerializerFeature.BrowserCompatible)) {
          if (ch == '\b' || ch == '\f' || ch == '\n' || ch == '\r' || ch == '\t' || ch == '"' || ch == '/' || ch == '\\') {
            write('\\');
            write(replaceChars[(int)ch]);
            continue;
          }
          if (ch < 32) {
            write('\\');
            write('u');
            write('0');
            write('0');
            write(IOUtils.ASCII_CHARS[ch * 2]);
            write(IOUtils.ASCII_CHARS[ch * 2 + 1]);
            continue;
          }
          if (ch >= 127) {
            write('\\');
            write('u');
            write(IOUtils.DIGITS[(ch >>> 12) & 15]);
            write(IOUtils.DIGITS[(ch >>> 8) & 15]);
            write(IOUtils.DIGITS[(ch >>> 4) & 15]);
            write(IOUtils.DIGITS[ch & 15]);
            continue;
          }
        }
 else {
          if (ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] != 0 || (ch == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
            write('\\');
            if (IOUtils.specicalFlags_doubleQuotes[ch] == 4) {
              write('u');
              write(IOUtils.DIGITS[ch >>> 12 & 15]);
              write(IOUtils.DIGITS[ch >>> 8 & 15]);
              write(IOUtils.DIGITS[ch >>> 4 & 15]);
              write(IOUtils.DIGITS[ch & 15]);
            }
 else {
              write(IOUtils.replaceChars[ch]);
            }
            continue;
          }
        }
        write(ch);
      }
      write('"');
      if (seperator != 0) {
        write(seperator);
      }
      return;
    }
    expandCapacity(newcount);
  }
  int start=count + 1;
  int end=start + len;
  buf[count]='\"';
  System.arraycopy(text,0,buf,start,text.length);
  count=newcount;
  if (isEnabled(SerializerFeature.BrowserCompatible)) {
    int lastSpecialIndex=-1;
    for (int i=start; i < end; ++i) {
      char ch=buf[i];
      if (ch == '"' || ch == '/' || ch == '\\') {
        lastSpecialIndex=i;
        newcount+=1;
        continue;
      }
      if (ch == '\b' || ch == '\f' || ch == '\n' || ch == '\r' || ch == '\t') {
        lastSpecialIndex=i;
        newcount+=1;
        continue;
      }
      if (ch < 32) {
        lastSpecialIndex=i;
        newcount+=5;
        continue;
      }
      if (ch >= 127) {
        lastSpecialIndex=i;
        newcount+=5;
        continue;
      }
    }
    if (newcount > buf.length) {
      expandCapacity(newcount);
    }
    count=newcount;
    for (int i=lastSpecialIndex; i >= start; --i) {
      char ch=buf[i];
      if (ch == '\b' || ch == '\f' || ch == '\n' || ch == '\r' || ch == '\t') {
        System.arraycopy(buf,i + 1,buf,i + 2,end - i - 1);
        buf[i]='\\';
        buf[i + 1]=replaceChars[(int)ch];
        end+=1;
        continue;
      }
      if (ch == '"' || ch == '/' || ch == '\\') {
        System.arraycopy(buf,i + 1,buf,i + 2,end - i - 1);
        buf[i]='\\';
        buf[i + 1]=ch;
        end+=1;
        continue;
      }
      if (ch < 32) {
        System.arraycopy(buf,i + 1,buf,i + 6,end - i - 1);
        buf[i]='\\';
        buf[i + 1]='u';
        buf[i + 2]='0';
        buf[i + 3]='0';
        buf[i + 4]=IOUtils.ASCII_CHARS[ch * 2];
        buf[i + 5]=IOUtils.ASCII_CHARS[ch * 2 + 1];
        end+=5;
        continue;
      }
      if (ch >= 127) {
        System.arraycopy(buf,i + 1,buf,i + 6,end - i - 1);
        buf[i]='\\';
        buf[i + 1]='u';
        buf[i + 2]=IOUtils.DIGITS[(ch >>> 12) & 15];
        buf[i + 3]=IOUtils.DIGITS[(ch >>> 8) & 15];
        buf[i + 4]=IOUtils.DIGITS[(ch >>> 4) & 15];
        buf[i + 5]=IOUtils.DIGITS[ch & 15];
        end+=5;
      }
    }
    if (seperator != 0) {
      buf[count - 2]='\"';
      buf[count - 1]=seperator;
    }
 else {
      buf[count - 1]='\"';
    }
    return;
  }
  int specialCount=0;
  int lastSpecialIndex=-1;
  int firstSpecialIndex=-1;
  char lastSpecial='\0';
  for (int i=start; i < end; ++i) {
    char ch=buf[i];
    if (ch >= ']') {
      if (ch >= 0x7F && (ch == '\u2028' || ch == '\u2029' || ch < 0xA0)) {
        if (firstSpecialIndex == -1) {
          firstSpecialIndex=i;
        }
        specialCount++;
        lastSpecialIndex=i;
        lastSpecial=ch;
        newcount+=4;
      }
      continue;
    }
    boolean special=(ch < 64 && (sepcialBits & (1L << ch)) != 0) || ch == '\\';
    if (special) {
      specialCount++;
      lastSpecialIndex=i;
      lastSpecial=ch;
      if (ch == '(' || ch == ')' || ch == '<' || ch == '>' || (ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] == 4)) {
        newcount+=4;
      }
      if (firstSpecialIndex == -1) {
        firstSpecialIndex=i;
      }
    }
  }
  if (specialCount > 0) {
    newcount+=specialCount;
    if (newcount > buf.length) {
      expandCapacity(newcount);
    }
    count=newcount;
    if (specialCount == 1) {
      if (lastSpecial == '\u2028') {
        int srcPos=lastSpecialIndex + 1;
        int destPos=lastSpecialIndex + 6;
        int LengthOfCopy=end - lastSpecialIndex - 1;
        System.arraycopy(buf,srcPos,buf,destPos,LengthOfCopy);
        buf[lastSpecialIndex]='\\';
        buf[++lastSpecialIndex]='u';
        buf[++lastSpecialIndex]='2';
        buf[++lastSpecialIndex]='0';
        buf[++lastSpecialIndex]='2';
        buf[++lastSpecialIndex]='8';
      }
 else       if (lastSpecial == '\u2029') {
        int srcPos=lastSpecialIndex + 1;
        int destPos=lastSpecialIndex + 6;
        int LengthOfCopy=end - lastSpecialIndex - 1;
        System.arraycopy(buf,srcPos,buf,destPos,LengthOfCopy);
        buf[lastSpecialIndex]='\\';
        buf[++lastSpecialIndex]='u';
        buf[++lastSpecialIndex]='2';
        buf[++lastSpecialIndex]='0';
        buf[++lastSpecialIndex]='2';
        buf[++lastSpecialIndex]='9';
      }
 else       if (lastSpecial == '(' || lastSpecial == ')' || lastSpecial == '<' || lastSpecial == '>') {
        int srcPos=lastSpecialIndex + 1;
        int destPos=lastSpecialIndex + 6;
        int LengthOfCopy=end - lastSpecialIndex - 1;
        System.arraycopy(buf,srcPos,buf,destPos,LengthOfCopy);
        buf[lastSpecialIndex]='\\';
        buf[++lastSpecialIndex]='u';
        final char ch=lastSpecial;
        buf[++lastSpecialIndex]=IOUtils.DIGITS[(ch >>> 12) & 15];
        buf[++lastSpecialIndex]=IOUtils.DIGITS[(ch >>> 8) & 15];
        buf[++lastSpecialIndex]=IOUtils.DIGITS[(ch >>> 4) & 15];
        buf[++lastSpecialIndex]=IOUtils.DIGITS[ch & 15];
      }
 else {
        final char ch=lastSpecial;
        if (ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] == 4) {
          int srcPos=lastSpecialIndex + 1;
          int destPos=lastSpecialIndex + 6;
          int LengthOfCopy=end - lastSpecialIndex - 1;
          System.arraycopy(buf,srcPos,buf,destPos,LengthOfCopy);
          int bufIndex=lastSpecialIndex;
          buf[bufIndex++]='\\';
          buf[bufIndex++]='u';
          buf[bufIndex++]=IOUtils.DIGITS[(ch >>> 12) & 15];
          buf[bufIndex++]=IOUtils.DIGITS[(ch >>> 8) & 15];
          buf[bufIndex++]=IOUtils.DIGITS[(ch >>> 4) & 15];
          buf[bufIndex++]=IOUtils.DIGITS[ch & 15];
        }
 else {
          int srcPos=lastSpecialIndex + 1;
          int destPos=lastSpecialIndex + 2;
          int LengthOfCopy=end - lastSpecialIndex - 1;
          System.arraycopy(buf,srcPos,buf,destPos,LengthOfCopy);
          buf[lastSpecialIndex]='\\';
          buf[++lastSpecialIndex]=replaceChars[(int)ch];
        }
      }
    }
 else     if (specialCount > 1) {
      int textIndex=firstSpecialIndex - start;
      int bufIndex=firstSpecialIndex;
      for (int i=textIndex; i < text.length; ++i) {
        char ch=text[i];
        if (browserSecure && (ch == '(' || ch == ')' || ch == '<' || ch == '>')) {
          buf[bufIndex++]='\\';
          buf[bufIndex++]='u';
          buf[bufIndex++]=IOUtils.DIGITS[(ch >>> 12) & 15];
          buf[bufIndex++]=IOUtils.DIGITS[(ch >>> 8) & 15];
          buf[bufIndex++]=IOUtils.DIGITS[(ch >>> 4) & 15];
          buf[bufIndex++]=IOUtils.DIGITS[ch & 15];
          end+=5;
        }
 else         if (ch < IOUtils.specicalFlags_doubleQuotes.length && IOUtils.specicalFlags_doubleQuotes[ch] != 0 || (ch == '/' && isEnabled(SerializerFeature.WriteSlashAsSpecial))) {
          buf[bufIndex++]='\\';
          if (IOUtils.specicalFlags_doubleQuotes[ch] == 4) {
            buf[bufIndex++]='u';
            buf[bufIndex++]=IOUtils.DIGITS[(ch >>> 12) & 15];
            buf[bufIndex++]=IOUtils.DIGITS[(ch >>> 8) & 15];
            buf[bufIndex++]=IOUtils.DIGITS[(ch >>> 4) & 15];
            buf[bufIndex++]=IOUtils.DIGITS[ch & 15];
            end+=5;
          }
 else {
            buf[bufIndex++]=replaceChars[(int)ch];
            end++;
          }
        }
 else {
          if (ch == '\u2028' || ch == '\u2029') {
            buf[bufIndex++]='\\';
            buf[bufIndex++]='u';
            buf[bufIndex++]=IOUtils.DIGITS[(ch >>> 12) & 15];
            buf[bufIndex++]=IOUtils.DIGITS[(ch >>> 8) & 15];
            buf[bufIndex++]=IOUtils.DIGITS[(ch >>> 4) & 15];
            buf[bufIndex++]=IOUtils.DIGITS[ch & 15];
            end+=5;
          }
 else {
            buf[bufIndex++]=ch;
          }
        }
      }
    }
  }
  if (seperator != 0) {
    buf[count - 2]='\"';
    buf[count - 1]=seperator;
  }
 else {
    buf[count - 1]='\"';
  }
}
