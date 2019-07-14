protected static String parsePhrase(final String lineText){
  boolean overloading=false;
{
    String trimmedLineText=lineText.trim();
    if (trimmedLineText.length() == 0)     return null;
    char lastChar=trimmedLineText.charAt(trimmedLineText.length() - 1);
    if (lastChar == '.') {
      trimmedLineText=trimmedLineText.substring(0,trimmedLineText.length() - 1).trim();
      if (trimmedLineText.length() == 0)       return null;
      lastChar=trimmedLineText.charAt(trimmedLineText.length() - 1);
switch (lastChar) {
case ')':
case ']':
case '"':
        break;
default :
      if (!Character.isJavaIdentifierPart(lastChar)) {
        return null;
      }
    break;
}
}
 else if (lastChar == '(') {
overloading=true;
}
 else if (!Character.isJavaIdentifierPart(lastChar)) {
return null;
}
}
final int currentCharIndex=lineText.length() - 1;
{
int commentStart=lineText.indexOf("//",0);
if (commentStart >= 0 && currentCharIndex > commentStart) {
return null;
}
}
BitSet isInLiteral=new BitSet(lineText.length());
BitSet isInBrackets=new BitSet(lineText.length());
{
boolean inString=false;
boolean inChar=false;
boolean inEscaped=false;
for (int i=0; i < lineText.length(); i++) {
if (!inEscaped) {
switch (lineText.charAt(i)) {
case '\"':
    if (!inChar)     inString=!inString;
  break;
case '\'':
if (!inString) inChar=!inChar;
break;
case '\\':
if (inString || inChar) {
inEscaped=true;
}
break;
}
}
 else {
inEscaped=false;
}
isInLiteral.set(i,inString || inChar);
}
}
if (isInLiteral.get(currentCharIndex)) return null;
{
int depth=overloading ? 1 : 0;
int bracketStart=overloading ? lineText.length() : 0;
int squareDepth=0;
int squareBracketStart=0;
bracketLoop: for (int i=lineText.length() - 1; i >= 0; i--) {
if (!isInLiteral.get(i)) {
switch (lineText.charAt(i)) {
case ')':
if (depth == 0) bracketStart=i;
depth++;
break;
case '(':
depth--;
if (depth == 0) {
isInBrackets.set(i,bracketStart);
}
 else if (depth < 0) {
break bracketLoop;
}
break;
case ']':
if (squareDepth == 0) squareBracketStart=i;
squareDepth++;
break;
case '[':
squareDepth--;
if (squareDepth == 0) {
isInBrackets.set(i,squareBracketStart);
}
 else if (squareDepth < 0) {
break bracketLoop;
}
break;
}
}
}
if (depth > 0) isInBrackets.set(0,bracketStart);
if (squareDepth > 0) isInBrackets.set(0,squareBracketStart);
}
int position=currentCharIndex;
parseLoop: while (position >= 0) {
int currChar=lineText.charAt(position);
switch (currChar) {
case '.':
position--;
break;
case '[':
break parseLoop;
case ']':
position=isInBrackets.previousClearBit(position - 1);
break;
case '(':
if (isInBrackets.get(position)) {
position--;
break;
}
break parseLoop;
case ')':
position=isInBrackets.previousClearBit(position - 1);
break;
case '"':
position=isInLiteral.previousClearBit(position - 1);
break parseLoop;
default :
if (Character.isJavaIdentifierPart(currChar)) {
position--;
}
 else if (Character.isWhitespace(currChar)) {
position--;
}
 else {
break parseLoop;
}
break;
}
}
position++;
String phrase=lineText.substring(position,lineText.length()).trim();
Messages.log(phrase);
if (phrase.length() == 0 || Character.isDigit(phrase.charAt(0))) {
return null;
}
return phrase;
}
