/** 
 * Joins a series into an entire row of comma separated values.
 * @param seriesData
 * @param separator
 * @return
 */
private static String join(double[] seriesData,String separator){
  StringBuilder sb=new StringBuilder(256);
  sb.append(seriesData[0]);
  for (int i=1; i < seriesData.length; i++) {
    if (separator != null) {
      sb.append(separator);
    }
    sb.append(seriesData[i]);
  }
  return sb.toString();
}
