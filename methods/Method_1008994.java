private static String indent(final int indentLevel,final String indentString,final String data){
  StringBuffer finalBuffer=new StringBuffer();
  StringBuffer indentPrefix=new StringBuffer();
  for (int j=0; j < indentLevel; j++) {
    indentPrefix.append(indentString);
  }
  LineNumberReader reader=new LineNumberReader(new StringReader(data));
  try {
    String line=reader.readLine();
    while (line != null) {
      finalBuffer.append(indentPrefix).append(line).append(_EOL);
      line=reader.readLine();
    }
  }
 catch (  IOException e) {
    finalBuffer.append(indentPrefix).append(e.getMessage()).append(_EOL);
  }
  return finalBuffer.toString();
}
