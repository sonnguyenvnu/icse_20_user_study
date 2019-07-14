private String filter(String input){
  return input.replaceAll("/\\*[\\s\\S]*?\\*/","");
}
