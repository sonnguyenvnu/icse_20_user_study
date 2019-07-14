/** 
 * Parses the given pattern and appends the rules to the given DateTimeFormatterBuilder.
 * @param pattern  pattern specification
 * @throws IllegalArgumentException if the pattern is invalid
 * @see #forPattern
 */
private static void parsePatternTo(DateTimeFormatterBuilder builder,String pattern){
  int length=pattern.length();
  int[] indexRef=new int[1];
  for (int i=0; i < length; i++) {
    indexRef[0]=i;
    String token=parseToken(pattern,indexRef);
    i=indexRef[0];
    int tokenLen=token.length();
    if (tokenLen == 0) {
      break;
    }
    char c=token.charAt(0);
switch (c) {
case 'G':
      builder.appendEraText();
    break;
case 'C':
  builder.appendCenturyOfEra(tokenLen,tokenLen);
break;
case 'x':
case 'y':
case 'Y':
if (tokenLen == 2) {
boolean lenientParse=true;
if (i + 1 < length) {
  indexRef[0]++;
  if (isNumericToken(parseToken(pattern,indexRef))) {
    lenientParse=false;
  }
  indexRef[0]--;
}
switch (c) {
case 'x':
  builder.appendTwoDigitWeekyear(new DateTime().getWeekyear() - 30,lenientParse);
break;
case 'y':
case 'Y':
default :
builder.appendTwoDigitYear(new DateTime().getYear() - 30,lenientParse);
break;
}
}
 else {
int maxDigits=9;
if (i + 1 < length) {
indexRef[0]++;
if (isNumericToken(parseToken(pattern,indexRef))) {
maxDigits=tokenLen;
}
indexRef[0]--;
}
switch (c) {
case 'x':
builder.appendWeekyear(tokenLen,maxDigits);
break;
case 'y':
builder.appendYear(tokenLen,maxDigits);
break;
case 'Y':
builder.appendYearOfEra(tokenLen,maxDigits);
break;
}
}
break;
case 'M':
if (tokenLen >= 3) {
if (tokenLen >= 4) {
builder.appendMonthOfYearText();
}
 else {
builder.appendMonthOfYearShortText();
}
}
 else {
builder.appendMonthOfYear(tokenLen);
}
break;
case 'd':
builder.appendDayOfMonth(tokenLen);
break;
case 'a':
builder.appendHalfdayOfDayText();
break;
case 'h':
builder.appendClockhourOfHalfday(tokenLen);
break;
case 'H':
builder.appendHourOfDay(tokenLen);
break;
case 'k':
builder.appendClockhourOfDay(tokenLen);
break;
case 'K':
builder.appendHourOfHalfday(tokenLen);
break;
case 'm':
builder.appendMinuteOfHour(tokenLen);
break;
case 's':
builder.appendSecondOfMinute(tokenLen);
break;
case 'S':
builder.appendFractionOfSecond(tokenLen,tokenLen);
break;
case 'e':
builder.appendDayOfWeek(tokenLen);
break;
case 'E':
if (tokenLen >= 4) {
builder.appendDayOfWeekText();
}
 else {
builder.appendDayOfWeekShortText();
}
break;
case 'D':
builder.appendDayOfYear(tokenLen);
break;
case 'w':
builder.appendWeekOfWeekyear(tokenLen);
break;
case 'z':
if (tokenLen >= 4) {
builder.appendTimeZoneName();
}
 else {
builder.appendTimeZoneShortName(null);
}
break;
case 'Z':
if (tokenLen == 1) {
builder.appendTimeZoneOffset(null,"Z",false,2,2);
}
 else if (tokenLen == 2) {
builder.appendTimeZoneOffset(null,"Z",true,2,2);
}
 else {
builder.appendTimeZoneId();
}
break;
case '\'':
String sub=token.substring(1);
if (sub.length() == 1) {
builder.appendLiteral(sub.charAt(0));
}
 else {
builder.appendLiteral(new String(sub));
}
break;
default :
throw new IllegalArgumentException("Illegal pattern component: " + token);
}
}
}
