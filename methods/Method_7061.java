private static String cyrillicToLatin(String s){
  final String alphabet="?????????????????????????????????";
  final String[] replacements={"A","B","V","G","D","E","E","ZH","Z","I","I","K","L","M","N","O","P","R","S","T","U","F","KH","TS","CH","SH","SHCH","IE","Y","","E","IU","IA"};
  for (int i=0; i < replacements.length; i++) {
    s=s.replace(alphabet.substring(i,i + 1),replacements[i]);
  }
  return s;
}
