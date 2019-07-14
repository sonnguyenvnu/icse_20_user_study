@Override public void tokenize(SourceCode sourceCode,Tokens tokenEntries){
  StringBuilder sb=sourceCode.getCodeBuffer();
  try (BufferedReader reader=new BufferedReader(new CharArrayReader(sb.toString().toCharArray()))){
    int lineNumber=1;
    String line=reader.readLine();
    while (line != null) {
      StringTokenizer tokenizer=new StringTokenizer(line,TOKENS,true);
      while (tokenizer.hasMoreTokens()) {
        String token=tokenizer.nextToken();
        if (!" ".equals(token) && !"\t".equals(token)) {
          tokenEntries.add(new TokenEntry(token,sourceCode.getFileName(),lineNumber));
        }
      }
      line=reader.readLine();
      lineNumber++;
    }
  }
 catch (  IOException ignored) {
    ignored.printStackTrace();
  }
 finally {
    tokenEntries.add(TokenEntry.getEOF());
  }
}
