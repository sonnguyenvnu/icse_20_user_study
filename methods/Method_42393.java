/** 
 * ???????????????
 * @param date ???????
 * @param start ????????
 * @param end ????????
 * @param interModel ????? <pre> ??? LEFT_OPEN_RIGHT_OPEN LEFT_CLOSE_RIGHT_OPEN LEFT_OPEN_RIGHT_CLOSE LEFT_CLOSE_RIGHT_CLOSE </pre>
 * @param compModel ????? <pre> ??? COMP_MODEL_DATE		??????????? COMP_MODEL_TIME		??????????? COMP_MODEL_DATETIME ?????????? </pre>
 * @return
 */
public static boolean isBetween(Date date,Date start,Date end,int interModel,int compModel){
  if (date == null || start == null || end == null) {
    throw new IllegalArgumentException("??????");
  }
  SimpleDateFormat format=null;
switch (compModel) {
case COMP_MODEL_DATE:
{
      format=new SimpleDateFormat("yyyyMMdd");
      break;
    }
case COMP_MODEL_TIME:
{
    format=new SimpleDateFormat("HHmmss");
    break;
  }
case COMP_MODEL_DATETIME:
{
  format=new SimpleDateFormat("yyyyMMddHHmmss");
  break;
}
default :
{
throw new IllegalArgumentException(String.format("???????[%d]??",compModel));
}
}
long dateNumber=Long.parseLong(format.format(date));
long startNumber=Long.parseLong(format.format(start));
long endNumber=Long.parseLong(format.format(end));
switch (interModel) {
case LEFT_OPEN_RIGHT_OPEN:
{
if (dateNumber <= startNumber || dateNumber >= endNumber) {
return false;
}
 else {
return true;
}
}
case LEFT_CLOSE_RIGHT_OPEN:
{
if (dateNumber < startNumber || dateNumber >= endNumber) {
return false;
}
 else {
return true;
}
}
case LEFT_OPEN_RIGHT_CLOSE:
{
if (dateNumber <= startNumber || dateNumber > endNumber) {
return false;
}
 else {
return true;
}
}
case LEFT_CLOSE_RIGHT_CLOSE:
{
if (dateNumber < startNumber || dateNumber > endNumber) {
return false;
}
 else {
return true;
}
}
default :
{
throw new IllegalArgumentException(String.format("???????[%d]??",interModel));
}
}
}
