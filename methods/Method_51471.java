private String cleaned(String original){
  String result=original.trim();
  result=result.replaceAll("\\s+"," ");
  result=result.replaceAll("\\s*[\\r\\n]+\\s*","");
  result=result.replaceAll("\"","'");
  return result;
}
