/** 
 * <p>Returns a list of Rules given a pattern.</p>
 * @return a {@code List} of Rule objects
 * @throws IllegalArgumentException if pattern is invalid
 */
protected List<Rule> parsePattern(){
  final DateFormatSymbols symbols=new DateFormatSymbols(mLocale);
  final List<Rule> rules=new ArrayList<Rule>();
  final String[] ERAs=symbols.getEras();
  final String[] months=symbols.getMonths();
  final String[] shortMonths=symbols.getShortMonths();
  final String[] weekdays=symbols.getWeekdays();
  final String[] shortWeekdays=symbols.getShortWeekdays();
  final String[] AmPmStrings=symbols.getAmPmStrings();
  final int length=mPattern.length();
  final int[] indexRef=new int[1];
  for (int i=0; i < length; i++) {
    indexRef[0]=i;
    final String token=parseToken(mPattern,indexRef);
    i=indexRef[0];
    final int tokenLen=token.length();
    if (tokenLen == 0) {
      break;
    }
    Rule rule;
    final char c=token.charAt(0);
switch (c) {
case 'G':
      rule=new TextField(Calendar.ERA,ERAs);
    break;
case 'y':
  if (tokenLen == 2) {
    rule=TwoDigitYearField.INSTANCE;
  }
 else {
    rule=selectNumberRule(Calendar.YEAR,tokenLen < 4 ? 4 : tokenLen);
  }
break;
case 'L':
if (tokenLen >= 4) {
rule=new TextField(Calendar.MONTH,months);
}
 else if (tokenLen == 3) {
rule=new TextField(Calendar.MONTH,shortMonths);
}
 else if (tokenLen == 2) {
rule=TwoDigitMonthField.INSTANCE;
}
 else {
rule=UnpaddedMonthField.INSTANCE;
}
break;
case 'M':
if (tokenLen >= 4) {
rule=new TextField(Calendar.MONTH,months);
}
 else if (tokenLen == 3) {
rule=new TextField(Calendar.MONTH,shortMonths);
}
 else if (tokenLen == 2) {
rule=TwoDigitMonthField.INSTANCE;
}
 else {
rule=UnpaddedMonthField.INSTANCE;
}
break;
case 'd':
rule=selectNumberRule(Calendar.DAY_OF_MONTH,tokenLen);
break;
case 'h':
rule=new TwelveHourField(selectNumberRule(Calendar.HOUR,tokenLen));
break;
case 'H':
rule=selectNumberRule(Calendar.HOUR_OF_DAY,tokenLen);
break;
case 'm':
rule=selectNumberRule(Calendar.MINUTE,tokenLen);
break;
case 's':
rule=selectNumberRule(Calendar.SECOND,tokenLen);
break;
case 'S':
rule=selectNumberRule(Calendar.MILLISECOND,tokenLen);
break;
case 'E':
rule=new TextField(Calendar.DAY_OF_WEEK,tokenLen < 4 ? shortWeekdays : weekdays);
break;
case 'D':
rule=selectNumberRule(Calendar.DAY_OF_YEAR,tokenLen);
break;
case 'F':
rule=selectNumberRule(Calendar.DAY_OF_WEEK_IN_MONTH,tokenLen);
break;
case 'w':
rule=selectNumberRule(Calendar.WEEK_OF_YEAR,tokenLen);
break;
case 'W':
rule=selectNumberRule(Calendar.WEEK_OF_MONTH,tokenLen);
break;
case 'a':
rule=new TextField(Calendar.AM_PM,AmPmStrings);
break;
case 'k':
rule=new TwentyFourHourField(selectNumberRule(Calendar.HOUR_OF_DAY,tokenLen));
break;
case 'K':
rule=selectNumberRule(Calendar.HOUR,tokenLen);
break;
case 'z':
if (tokenLen >= 4) {
rule=new TimeZoneNameRule(mTimeZone,mLocale,TimeZone.LONG);
}
 else {
rule=new TimeZoneNameRule(mTimeZone,mLocale,TimeZone.SHORT);
}
break;
case 'Z':
if (tokenLen == 1) {
rule=TimeZoneNumberRule.INSTANCE_NO_COLON;
}
 else {
rule=TimeZoneNumberRule.INSTANCE_COLON;
}
break;
case '\'':
final String sub=token.substring(1);
if (sub.length() == 1) {
rule=new CharacterLiteral(sub.charAt(0));
}
 else {
rule=new StringLiteral(sub);
}
break;
default :
throw new IllegalArgumentException("Illegal pattern component: " + token);
}
rules.add(rule);
}
return rules;
}
