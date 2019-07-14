/** 
 * Replace the source code between the start and end lines with some new lines of code. 
 */
public void replaceLines(int startLine,int endLine,List<String> replacementLines){
  Preconditions.checkArgument(startLine <= endLine);
  List<String> originalLines=getLines();
  List<String> newLines=new ArrayList<>();
  for (int i=0; i < originalLines.size(); i++) {
    int lineNum=i + 1;
    if (lineNum == startLine) {
      newLines.addAll(replacementLines);
    }
 else     if (lineNum > startLine && lineNum <= endLine) {
    }
 else {
      newLines.add(originalLines.get(i));
    }
  }
  replaceLines(newLines);
}
