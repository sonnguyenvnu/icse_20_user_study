/** 
 * Get a list of all the numbers in this sketch
 * @return list of all numbers in the sketch (excluding hexadecimals)
 */
private void addAllDecimalNumbers(){
  Pattern p=Pattern.compile("[\\[\\{<>(),\\t\\s\\+\\-\\/\\*^%!|&=?:~]\\d+\\.?\\d*");
  for (int i=0; i < codeTabs.length; i++) {
    List<Handle> handles=new ArrayList<>();
    allHandles.add(handles);
    String c=codeTabs[i];
    Matcher m=p.matcher(c);
    while (m.find()) {
      boolean forceFloat=false;
      int start=m.start() + 1;
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
      boolean found=false;
      for (      Range r : scientificNotations.get(i)) {
        if (r.contains(start)) {
          found=true;
          break;
        }
      }
      if (found) {
        continue;
      }
      if (c.charAt(end) == 'f') {
        forceFloat=true;
        end++;
      }
      if (c.charAt(start - 1) == '-') {
        if (isNegativeSign(start - 2,c)) {
          start--;
        }
      }
      if (c.charAt(m.end()) == 'x' || c.charAt(m.end()) == 'X') {
        continue;
      }
      if (isInsideString(start,c))       continue;
      if (isGlobal(m.start(),i))       continue;
      int line=countLines(c.substring(0,start)) - 1;
      String value=c.substring(start,end);
      if (value.contains(".") || forceFloat) {
        String name=varPrefix + "_float[" + floatVarCount + "]";
        int decimalDigits=getNumDigitsAfterPoint(value);
        handles.add(new Handle("float",name,floatVarCount,value,i,line,start,end,decimalDigits));
        floatVarCount++;
      }
 else {
        String name=varPrefix + "_int[" + intVarCount + "]";
        handles.add(new Handle("int",name,intVarCount,value,i,line,start,end,0));
        intVarCount++;
      }
    }
  }
}
