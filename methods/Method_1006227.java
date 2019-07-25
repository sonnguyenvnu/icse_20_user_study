@Override public String format(String field){
  StringBuilder sb=new StringBuilder("");
  StringBuilder currentCommand=null;
  boolean escaped=false;
  boolean incommand=false;
  for (int i=0; i < field.length(); i++) {
    char c=field.charAt(i);
    if (escaped && (c == '\\')) {
      sb.append('\\');
      escaped=false;
    }
 else     if (c == '\\') {
      escaped=true;
      incommand=true;
      currentCommand=new StringBuilder();
    }
 else     if (!incommand && ((c == '{') || (c == '}'))) {
    }
 else     if (Character.isLetter(c) || StringUtil.SPECIAL_COMMAND_CHARS.contains(String.valueOf(c))) {
      escaped=false;
      if (incommand) {
        currentCommand.append(c);
        testCharCom:         if ((currentCommand.length() == 1) && StringUtil.SPECIAL_COMMAND_CHARS.contains(currentCommand.toString())) {
          if (i >= (field.length() - 1)) {
            break testCharCom;
          }
          String command=currentCommand.toString();
          i++;
          c=field.charAt(i);
          String combody;
          if (c == '{') {
            StringInt part=getPart(field,i,true);
            i+=part.i;
            combody=part.s;
          }
 else {
            combody=field.substring(i,i + 1);
          }
          String result=RTF_CHARS.get(command + combody);
          if (result != null) {
            sb.append(result);
          }
          incommand=false;
          escaped=false;
        }
      }
 else {
        sb.append(c);
      }
    }
 else {
      testContent:       if (!incommand || (!Character.isWhitespace(c) && (c != '{') && (c != '}'))) {
        sb.append(c);
      }
 else {
        assert incommand;
        if ((c == '{') && (currentCommand.length() == 0)) {
          continue;
        }
 else         if ((c == '}') && (currentCommand.length() > 0)) {
          String command=currentCommand.toString();
          String result=RTF_CHARS.get(command);
          if (result != null) {
            sb.append(result);
          }
          incommand=false;
          escaped=false;
          continue;
        }
        if (i >= (field.length() - 1)) {
          break testContent;
        }
        if (((c == '{') || (c == ' ')) && (currentCommand.length() > 0)) {
          String command=currentCommand.toString();
          if ("em".equals(command) || "emph".equals(command) || "textit".equals(command) || "it".equals(command)) {
            StringInt part=getPart(field,i,c == '{');
            i+=part.i;
            sb.append("{\\i ").append(part.s).append('}');
          }
 else           if ("textbf".equals(command) || "bf".equals(command)) {
            StringInt part=getPart(field,i,c == '{');
            i+=part.i;
            sb.append("{\\b ").append(part.s).append('}');
          }
 else {
            LOGGER.info("Unknown command " + command);
          }
          if (c == ' ') {
          }
        }
 else {
          sb.append(c);
        }
      }
      incommand=false;
      escaped=false;
    }
  }
  char[] chars=sb.toString().toCharArray();
  sb=new StringBuilder();
  for (  char c : chars) {
    if (c < 128) {
      sb.append(c);
    }
 else {
      sb.append("\\u").append((long)c).append(transformSpecialCharacter(c));
    }
  }
  return sb.toString().replace("---","{\\emdash}").replace("--","{\\endash}").replace("``","{\\ldblquote}").replace("''","{\\rdblquote}");
}
