/** 
 * The source location of the ast node to print, input or output. 
 */
public static String generate(Map<SourcePosition,SourcePosition> javaSourcePositionByOutputSourcePosition,String javaScriptImplementationFileContents,NativeJavaScriptFile nativeJavaScriptFile,String j2clUnitFilePath,Problems problems){
  Map<String,List<String>> sourceLinesByFileName=buildSourceLinesByFileName(nativeJavaScriptFile,j2clUnitFilePath,problems);
  StringBuilder sb=new StringBuilder();
  List<Entry<SourcePosition,SourcePosition>> entries=new ArrayList<>(javaSourcePositionByOutputSourcePosition.entrySet());
  SourcePosition eofMarker=entries.get(entries.size() - 1).getValue();
  checkState(eofMarker.getEndFilePosition().equals(eofMarker.getStartFilePosition()));
  entries.remove(entries.size() - 1);
  List<String> javaScriptSourceLines=Arrays.asList(javaScriptImplementationFileContents.split("\n"));
  for (  Entry<SourcePosition,SourcePosition> entry : entries) {
    SourcePosition javaSourcePosition=checkNotNull(entry.getValue());
    SourcePosition javaScriptSourcePosition=checkNotNull(entry.getKey());
    List<String> javaSourceLines=sourceLinesByFileName.get(javaSourcePosition.getFileName());
    boolean hasName=javaSourcePosition.getName() != null;
    sb.append(extract(javaSourcePosition,javaSourceLines,hasName)).append(" => ").append(extract(javaScriptSourcePosition,javaScriptSourceLines,hasName).trim());
    if (hasName) {
      sb.append(" \"").append(javaSourcePosition.getName()).append("\"");
    }
    sb.append("\n");
  }
  return sb.toString();
}
