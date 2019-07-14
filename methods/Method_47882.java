/** 
 * Returns the values of the scores that fall inside a certain interval of time. <p> The values are returned in an array containing one integer value for each day of the interval. The first entry corresponds to the most recent day in the interval. Each subsequent entry corresponds to one day older than the previous entry. The boundaries of the time interval are included.
 * @param from timestamp for the oldest score
 * @param to   timestamp for the newest score
 * @return values for the scores inside the given interval
 */
public final double[] getValues(Timestamp from,Timestamp to){
  List<Score> scores=getByInterval(from,to);
  double[] values=new double[scores.size()];
  for (int i=0; i < values.length; i++)   values[i]=scores.get(i).getValue();
  return values;
}
