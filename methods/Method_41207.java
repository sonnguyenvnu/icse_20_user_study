/** 
 * <p> Redefine a certain day of the week to be excluded (true) or included (false). Use java.util.Calendar's constants like MONDAY to determine the wday. </p>
 */
public void setDayExcluded(int wday,boolean exclude){
  excludeDays[wday]=exclude;
  excludeAll=areAllDaysExcluded();
}
