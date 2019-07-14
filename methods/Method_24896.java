private static void checkForUnterminatedMultilineComment(final String program) throws SketchException {
  final int length=program.length();
  for (int i=0; i < length; i++) {
    if ((program.charAt(i) == '/') && (i < length - 1) && (program.charAt(i + 1) == '/')) {
      i+=2;
      while ((i < length) && (program.charAt(i) != '\n')) {
        i++;
      }
    }
 else     if ((program.charAt(i) == '/') && (i < length - 1) && (program.charAt(i + 1) == '*')) {
      final int startOfComment=i;
      i+=2;
      boolean terminated=false;
      while (i < length - 1) {
        if ((program.charAt(i) == '*') && (program.charAt(i + 1) == '/')) {
          i++;
          terminated=true;
          break;
        }
 else {
          i++;
        }
      }
      if (!terminated) {
        throw new SketchException("Unclosed /* comment */",0,countNewlines(program.substring(0,startOfComment)));
      }
    }
 else     if (program.charAt(i) == '"') {
      final int stringStart=i;
      boolean terminated=false;
      for (i++; i < length; i++) {
        final char c=program.charAt(i);
        if (c == '"') {
          terminated=true;
          break;
        }
 else         if (c == '\\') {
          if (i == length - 1) {
            break;
          }
          i++;
        }
 else         if (c == '\n') {
          break;
        }
      }
      if (!terminated) {
        throw new SketchException("Unterminated string constant",0,countNewlines(program.substring(0,stringStart)));
      }
    }
 else     if (program.charAt(i) == '\'') {
      i++;
      if (i >= length) {
        throw new SketchException("Unterminated character constant (after initial quote)",0,countNewlines(program.substring(0,i)));
      }
      boolean escaped=false;
      if (program.charAt(i) == '\\') {
        i++;
        escaped=true;
      }
      if (i >= length) {
        throw new SketchException("Unterminated character constant (after backslash)",0,countNewlines(program.substring(0,i)));
      }
      if (escaped && program.charAt(i) == 'u') {
        i++;
        for (int j=0; j < 4; j++) {
          if (UNICODE_ESCAPES.indexOf(program.charAt(i)) == -1) {
            throw new SketchException("Bad or unfinished \\uXXXX sequence " + "(malformed Unicode character constant)",0,countNewlines(program.substring(0,i)));
          }
          i++;
        }
      }
 else {
        i++;
      }
      if (i >= length) {
        throw new SketchException("Unterminated character constant",0,countNewlines(program.substring(0,i)));
      }
      if (program.charAt(i) != '\'') {
        throw new SketchException("Badly formed character constant " + "(expecting quote, got " + program.charAt(i) + ")",0,countNewlines(program.substring(0,i)));
      }
    }
  }
}
