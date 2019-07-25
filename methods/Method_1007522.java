private static String[] map(String[] words){
  if (words == null)   return words;
  String[] mod=new String[words.length];
  for (int ii=0; ii < mod.length; ii++)   mod[ii]=words[ii].replace("/",".");
  return mod;
}
