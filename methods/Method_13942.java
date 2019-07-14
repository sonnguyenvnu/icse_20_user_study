/** 
 * Returns the list of aggregated warnings, ordered by decreasing severity
 */
@JsonProperty("warnings") public List<QAWarning> getWarnings(){
  List<QAWarning> result=new ArrayList<>(map.values());
  Collections.sort(result);
  return result;
}
