/** 
 * Returns the values of the checkmarks that fall inside a certain interval of time. <p> The values are returned in an array containing one integer value for each day of the interval. The first entry corresponds to the most recent day in the interval. Each subsequent entry corresponds to one day older than the previous entry. The boundaries of the time interval are included.
 * @param from timestamp for the oldest checkmark
 * @param to   timestamp for the newest checkmark
 * @return values for the checkmarks inside the given interval
 */
public final int[] getValues(Timestamp from,Timestamp to){
  if (from.isNewerThan(to))   return new int[0];
  List<Checkmark> checkmarks=getByInterval(from,to);
  int values[]=new int[checkmarks.size()];
  int i=0;
  for (  Checkmark c : checkmarks)   values[i++]=c.getValue();
  return values;
}
