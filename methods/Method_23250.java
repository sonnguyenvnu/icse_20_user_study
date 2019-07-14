static public String[] loadStrings(BufferedReader reader){
  try {
    String lines[]=new String[100];
    int lineCount=0;
    String line=null;
    while ((line=reader.readLine()) != null) {
      if (lineCount == lines.length) {
        String temp[]=new String[lineCount << 1];
        System.arraycopy(lines,0,temp,0,lineCount);
        lines=temp;
      }
      lines[lineCount++]=line;
    }
    reader.close();
    if (lineCount == lines.length) {
      return lines;
    }
    String output[]=new String[lineCount];
    System.arraycopy(lines,0,output,0,lineCount);
    return output;
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
  return null;
}
