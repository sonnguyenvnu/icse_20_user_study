@NotNull private static List<String> split(@NotNull String s,@NotNull ChooseByNameBase base){
  List<String> answer=new ArrayList<>();
  for (  String token : StringUtil.tokenize(s,StringUtil.join(base.getModel().getSeparators(),""))) {
    if (!token.isEmpty()) {
      answer.add(token);
    }
  }
  return answer.isEmpty() ? Collections.singletonList(s) : answer;
}
