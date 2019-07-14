/** 
 * Checks a single code fragment (such as a tab) for non-matching braces. Broken out to allow easy use in JavaBuild.
 * @param c Program code scrubbed of comments and string literals.
 * @param start Start index, inclusive.
 * @param end End index, exclusive.
 * @return {@code int[4]} Depth at which the loop stopped, followed by theline number, column, and string index (within the range) at which an error was found, if any.
 */
static public int[] checkForMissingBraces(CharSequence c,int start,int end){
  int depth=0;
  int lineNumber=0;
  int lineStart=start;
  for (int i=start; i < end; i++) {
    char ch=c.charAt(i);
switch (ch) {
case '{':
      depth++;
    break;
case '}':
  depth--;
break;
case '\n':
lineNumber++;
lineStart=i;
break;
}
if (depth < 0) {
return new int[]{depth,lineNumber,i - lineStart,i - start};
}
}
return new int[]{depth,lineNumber - 1,end - lineStart - 2,end - start - 2};
}
