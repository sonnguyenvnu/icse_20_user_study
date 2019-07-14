/** 
 * Adds a cutover for added rules. The standard offset at the cutover defaults to 0. Call setStandardOffset afterwards to change it.
 * @param year  the year of cutover
 * @param mode 'u' - cutover is measured against UTC, 'w' - against walloffset, 's' - against standard offset
 * @param monthOfYear  the month from 1 (January) to 12 (December)
 * @param dayOfMonth  if negative, set to ((last day of month) - ~dayOfMonth).For example, if -1, set to last day of month
 * @param dayOfWeek  from 1 (Monday) to 7 (Sunday), if 0 then ignore
 * @param advanceDayOfWeek  if dayOfMonth does not fall on dayOfWeek, advance todayOfWeek when true, retreat when false.
 * @param millisOfDay  additional precision for specifying time of day of cutover
 */
public DateTimeZoneBuilder addCutover(int year,char mode,int monthOfYear,int dayOfMonth,int dayOfWeek,boolean advanceDayOfWeek,int millisOfDay){
  if (iRuleSets.size() > 0) {
    OfYear ofYear=new OfYear(mode,monthOfYear,dayOfMonth,dayOfWeek,advanceDayOfWeek,millisOfDay);
    RuleSet lastRuleSet=iRuleSets.get(iRuleSets.size() - 1);
    lastRuleSet.setUpperLimit(year,ofYear);
  }
  iRuleSets.add(new RuleSet());
  return this;
}
