protected void assemble(Fields fields){
  Object[] params=(Object[])getParam();
  JulianChronology julian=(JulianChronology)params[0];
  GregorianChronology gregorian=(GregorianChronology)params[1];
  Instant cutoverInstant=(Instant)params[2];
  iCutoverMillis=cutoverInstant.getMillis();
  iJulianChronology=julian;
  iGregorianChronology=gregorian;
  iCutoverInstant=cutoverInstant;
  if (getBase() != null) {
    return;
  }
  if (julian.getMinimumDaysInFirstWeek() != gregorian.getMinimumDaysInFirstWeek()) {
    throw new IllegalArgumentException();
  }
  iGapDuration=iCutoverMillis - julianToGregorianByYear(iCutoverMillis);
  fields.copyFieldsFrom(gregorian);
  if (gregorian.millisOfDay().get(iCutoverMillis) == 0) {
    fields.millisOfSecond=new CutoverField(julian.millisOfSecond(),fields.millisOfSecond,iCutoverMillis);
    fields.millisOfDay=new CutoverField(julian.millisOfDay(),fields.millisOfDay,iCutoverMillis);
    fields.secondOfMinute=new CutoverField(julian.secondOfMinute(),fields.secondOfMinute,iCutoverMillis);
    fields.secondOfDay=new CutoverField(julian.secondOfDay(),fields.secondOfDay,iCutoverMillis);
    fields.minuteOfHour=new CutoverField(julian.minuteOfHour(),fields.minuteOfHour,iCutoverMillis);
    fields.minuteOfDay=new CutoverField(julian.minuteOfDay(),fields.minuteOfDay,iCutoverMillis);
    fields.hourOfDay=new CutoverField(julian.hourOfDay(),fields.hourOfDay,iCutoverMillis);
    fields.hourOfHalfday=new CutoverField(julian.hourOfHalfday(),fields.hourOfHalfday,iCutoverMillis);
    fields.clockhourOfDay=new CutoverField(julian.clockhourOfDay(),fields.clockhourOfDay,iCutoverMillis);
    fields.clockhourOfHalfday=new CutoverField(julian.clockhourOfHalfday(),fields.clockhourOfHalfday,iCutoverMillis);
    fields.halfdayOfDay=new CutoverField(julian.halfdayOfDay(),fields.halfdayOfDay,iCutoverMillis);
  }
{
    fields.era=new CutoverField(julian.era(),fields.era,iCutoverMillis);
  }
{
    fields.year=new ImpreciseCutoverField(julian.year(),fields.year,iCutoverMillis);
    fields.years=fields.year.getDurationField();
    fields.yearOfEra=new ImpreciseCutoverField(julian.yearOfEra(),fields.yearOfEra,fields.years,iCutoverMillis);
    fields.centuryOfEra=new ImpreciseCutoverField(julian.centuryOfEra(),fields.centuryOfEra,iCutoverMillis);
    fields.centuries=fields.centuryOfEra.getDurationField();
    fields.yearOfCentury=new ImpreciseCutoverField(julian.yearOfCentury(),fields.yearOfCentury,fields.years,fields.centuries,iCutoverMillis);
    fields.monthOfYear=new ImpreciseCutoverField(julian.monthOfYear(),fields.monthOfYear,null,fields.years,iCutoverMillis);
    fields.months=fields.monthOfYear.getDurationField();
    fields.weekyear=new ImpreciseCutoverField(julian.weekyear(),fields.weekyear,null,iCutoverMillis,true);
    fields.weekyears=fields.weekyear.getDurationField();
    fields.weekyearOfCentury=new ImpreciseCutoverField(julian.weekyearOfCentury(),fields.weekyearOfCentury,fields.weekyears,fields.centuries,iCutoverMillis);
  }
{
    long cutover=gregorian.year().roundCeiling(iCutoverMillis);
    fields.dayOfYear=new CutoverField(julian.dayOfYear(),fields.dayOfYear,fields.years,cutover,false);
  }
{
    long cutover=gregorian.weekyear().roundCeiling(iCutoverMillis);
    fields.weekOfWeekyear=new CutoverField(julian.weekOfWeekyear(),fields.weekOfWeekyear,fields.weekyears,cutover,true);
  }
{
    CutoverField cf=new CutoverField(julian.dayOfMonth(),fields.dayOfMonth,iCutoverMillis);
    cf.iRangeDurationField=fields.months;
    fields.dayOfMonth=cf;
  }
}
