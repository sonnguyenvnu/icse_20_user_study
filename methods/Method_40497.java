/** 
 * Report resolution rate and other statistics data.
 */
public String getStatusReport(){
  StringBuilder sb=new StringBuilder();
  sb.append("Summary: \n").append("- modules loaded:\t").append(loadedFiles).append("\n- unresolved modules:\t").append(unresolvedModules.size()).append("\n- semantics problems:\t").append(nprob).append("\n- parsing problems:\t").append(nparsing);
  return sb.toString();
}
