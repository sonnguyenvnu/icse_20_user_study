private String getLegalStr(){
  StringBuilder sb=new StringBuilder();
  try {
    BufferedReader reader=new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/distfiles/Procyon.License.txt")));
    String line;
    while ((line=reader.readLine()) != null)     sb.append(line).append("\n");
    sb.append("\n\n\n\n\n");
    reader=new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/distfiles/RSyntaxTextArea.License.txt")));
    while ((line=reader.readLine()) != null)     sb.append(line).append("\n");
  }
 catch (  IOException e) {
    Luyten.showExceptionDialog("Exception!",e);
  }
  return sb.toString();
}
