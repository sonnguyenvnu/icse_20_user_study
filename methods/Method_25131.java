/** 
 * Replace the source code with the new lines of code. 
 */
public void replaceLines(List<String> lines){
  sourceBuilder.replace(0,sourceBuilder.length(),Joiner.on("\n").join(lines) + "\n");
}
