/** 
 * Loads properties from the specified InputStream. The properties are of the form <code>key=value</code>, one property per line. It may be not encode as 'ISO-8859-1'.The  {@code Properties} file is interpretedaccording to the following rules: <ul> <li>Empty lines are ignored.</li> <li>Lines starting with either a "#" or a "!" are comment lines and are ignored.</li> <li>A backslash at the end of the line escapes the following newline character ("\r", "\n", "\r\n"). If there's a whitespace after the backslash it will just escape that whitespace instead of concatenating the lines. This does not apply to comment lines.</li> <li>A property line consists of the key, the space between the key and the value, and the value. The key goes up to the first whitespace, "=" or ":" that is not escaped. The space between the key and the value contains either one whitespace, one "=" or one ":" and any number of additional whitespaces before and after that character. The value starts with the first character after the space between the key and the value.</li> <li>Following escape sequences are recognized: "\ ", "\\", "\r", "\n", "\!", "\#", "\t", "\b", "\f", and "&#92;uXXXX" (unicode character).</li> </ul>
 * @param reader Reader from which to read the properties of this CrashReportData.
 * @return CrashReportData read from the supplied Reader.
 * @throws java.io.IOException if the properties could not be read.
 * @since 1.6
 */
@NonNull private synchronized CrashReportData legacyLoad(@NonNull Reader reader) throws IOException {
  int mode=NONE, unicode=0, count=0;
  char nextChar;
  char[] buf=new char[40];
  int offset=0, keyLength=-1, intVal;
  boolean firstChar=true;
  final CrashReportData crashData=new CrashReportData();
  final BufferedReader br=new BufferedReader(reader,ACRAConstants.DEFAULT_BUFFER_SIZE_IN_BYTES);
  try {
    while (true) {
      intVal=br.read();
      if (intVal == -1) {
        break;
      }
      nextChar=(char)intVal;
      if (offset == buf.length) {
        final char[] newBuf=new char[buf.length * 2];
        System.arraycopy(buf,0,newBuf,0,offset);
        buf=newBuf;
      }
      if (mode == UNICODE) {
        final int digit=Character.digit(nextChar,16);
        if (digit >= 0) {
          unicode=(unicode << 4) + digit;
          if (++count < 4) {
            continue;
          }
        }
 else         if (count <= 4) {
          throw new IllegalArgumentException("luni.09");
        }
        mode=NONE;
        buf[offset++]=(char)unicode;
        if (nextChar != '\n' && nextChar != '\u0085') {
          continue;
        }
      }
      if (mode == SLASH) {
        mode=NONE;
switch (nextChar) {
case '\r':
          mode=CONTINUE;
        continue;
case '\u0085':
case '\n':
      mode=IGNORE;
    continue;
case 'b':
  nextChar='\b';
break;
case 'f':
nextChar='\f';
break;
case 'n':
nextChar='\n';
break;
case 'r':
nextChar='\r';
break;
case 't':
nextChar='\t';
break;
case 'u':
mode=UNICODE;
unicode=count=0;
continue;
}
}
 else {
switch (nextChar) {
case '#':
case '!':
if (firstChar) {
while (true) {
intVal=br.read();
if (intVal == -1) {
break;
}
nextChar=(char)intVal;
if (nextChar == '\r' || nextChar == '\n' || nextChar == '\u0085') {
break;
}
}
continue;
}
break;
case '\n':
if (mode == CONTINUE) {
mode=IGNORE;
continue;
}
case '\u0085':
case '\r':
mode=NONE;
firstChar=true;
if (offset > 0 || (offset == 0 && keyLength == 0)) {
if (keyLength == -1) {
keyLength=offset;
}
final String temp=new String(buf,0,offset);
putKeyValue(crashData,temp.substring(0,keyLength),temp.substring(keyLength));
}
keyLength=-1;
offset=0;
continue;
case '\\':
if (mode == KEY_DONE) {
keyLength=offset;
}
mode=SLASH;
continue;
case ':':
case '=':
if (keyLength == -1) {
mode=NONE;
keyLength=offset;
continue;
}
break;
}
if (Character.isWhitespace(nextChar)) {
if (mode == CONTINUE) {
mode=IGNORE;
}
if (offset == 0 || offset == keyLength || mode == IGNORE) {
continue;
}
if (keyLength == -1) {
mode=KEY_DONE;
continue;
}
}
if (mode == IGNORE || mode == CONTINUE) {
mode=NONE;
}
}
firstChar=false;
if (mode == KEY_DONE) {
keyLength=offset;
mode=NONE;
}
buf[offset++]=nextChar;
}
if (mode == UNICODE && count <= 4) {
throw new IllegalArgumentException("luni.08");
}
if (keyLength == -1 && offset > 0) {
keyLength=offset;
}
if (keyLength >= 0) {
final String temp=new String(buf,0,offset);
String value=temp.substring(keyLength);
if (mode == SLASH) {
value+="\u0000";
}
putKeyValue(crashData,temp.substring(0,keyLength),value);
}
IOUtils.safeClose(reader);
return crashData;
}
  finally {
IOUtils.safeClose(br);
}
}
