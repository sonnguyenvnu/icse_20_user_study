/** 
 * Replace all commented portions of a given String as spaces. Utility function used here and in the preprocessor.
 */
static public String scrubComments(String what){
  char p[]=what.toCharArray();
  boolean insideQuote=false;
  int index=0;
  while (index < p.length) {
    if (!insideQuote && (p[index] == '/') && (index < p.length - 1) && (p[index + 1] == '/')) {
      p[index++]=' ';
      p[index++]=' ';
      while ((index < p.length) && (p[index] != '\n')) {
        p[index++]=' ';
      }
    }
 else     if (!insideQuote && (p[index] == '/') && (index < p.length - 1) && (p[index + 1] == '*')) {
      p[index++]=' ';
      p[index++]=' ';
      boolean endOfRainbow=false;
      while (index < p.length - 1) {
        if ((p[index] == '*') && (p[index + 1] == '/')) {
          p[index++]=' ';
          p[index++]=' ';
          endOfRainbow=true;
          break;
        }
 else {
          p[index++]=' ';
        }
      }
      if (!endOfRainbow) {
        throw new RuntimeException("Missing the */ from the end of a " + "/* comment */");
      }
    }
 else     if (p[index] == '"') {
      insideQuote=!insideQuote;
      index++;
    }
 else     if (insideQuote && p[index] == '\\') {
      index+=2;
    }
 else {
      index++;
    }
  }
  return new String(p);
}
