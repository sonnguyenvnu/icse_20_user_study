public static List<String> extractEmojis(String input){
  List<UnicodeCandidate> emojis=getUnicodeCandidates(input);
  List<String> result=new ArrayList<String>();
  for (  UnicodeCandidate emoji : emojis) {
    result.add(emoji.getEmoji().getUnicode());
  }
  return result;
}
