/** 
 * Get a list of all the webcolors (#) numbers in the code list of all hexadecimal numbers in the sketch
 */
private void addAllWebColorNumbers(){
  Pattern p=Pattern.compile("#[A-Fa-f0-9]{6}");
  for (int i=0; i < codeTabs.length; i++) {
    String c=codeTabs[i];
    Matcher m=p.matcher(c);
    while (m.find()) {
      int start=m.start();
      int end=m.end();
      if (isInRangeList(start,commentBlocks.get(i))) {
        continue;
      }
      if (isInRangeList(start,ignoreFunctions.get(i))) {
        continue;
      }
      if (requiresComment) {
        if (!lineHasTweakComment(start,c)) {
          continue;
        }
      }
      if (isInsideString(start,c)) {
        continue;
      }
      if (isGlobal(m.start(),i)) {
        continue;
      }
      int line=countLines(c.substring(0,start)) - 1;
      String value=c.substring(start,end);
      String name=varPrefix + "_int[" + intVarCount + "]";
      Handle handle;
      try {
        handle=new Handle("webcolor",name,intVarCount,value,i,line,start,end,0);
      }
 catch (      NumberFormatException e) {
        continue;
      }
      allHandles.get(i).add(handle);
      intVarCount++;
    }
  }
}
