/** 
 * Gets a type that defines just the years field.
 * @return the period type
 */
public static PeriodType years(){
  PeriodType type=cYears;
  if (type == null) {
    type=new PeriodType("Years",new DurationFieldType[]{DurationFieldType.years()},new int[]{0,-1,-1,-1,-1,-1,-1,-1});
    cYears=type;
  }
  return type;
}
