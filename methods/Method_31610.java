@Override public int read() throws IOException {
  if (replacement == null) {
    if (buffer.length() > 0) {
      char c=buffer.charAt(0);
      buffer.deleteCharAt(0);
      return c;
    }
    int r;
    do {
      r=super.read();
      if (r == -1) {
        break;
      }
      buffer.append((char)r);
    }
 while (buffer.length() < prefix.length() && endsWith(buffer,prefix.substring(0,buffer.length())));
    if (!endsWith(buffer,prefix)) {
      if (buffer.length() > 0) {
        char c=buffer.charAt(0);
        buffer.deleteCharAt(0);
        return c;
      }
      return -1;
    }
    buffer.delete(0,buffer.length());
    StringBuilder placeholder=new StringBuilder();
    do {
      int r1=in.read();
      if (r1 == -1) {
        break;
      }
 else {
        placeholder.append((char)r1);
      }
    }
 while (!endsWith(placeholder,suffix));
    for (int i=0; i < suffix.length(); i++) {
      placeholder.deleteCharAt(placeholder.length() - 1);
    }
    replacement=placeholders.get(placeholder.toString());
    if (replacement == null) {
      throw new FlywayException("No value provided for placeholder: " + prefix + placeholder + suffix + ".  Check your configuration!");
    }
    if (replacement.length() == 0) {
      replacement=null;
      return read();
    }
  }
  int result=replacement.charAt(replacementPos);
  replacementPos++;
  if (replacementPos >= replacement.length()) {
    replacement=null;
    replacementPos=0;
  }
  return result;
}
