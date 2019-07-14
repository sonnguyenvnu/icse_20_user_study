/** 
 * ??View
 * @param list
 */
public void setView(List<User> list){
  if (list == null || list.isEmpty()) {
    setText("");
    return;
  }
  int count=list == null ? 0 : list.size();
  if (count > 9) {
    list=list.subList(0,9);
  }
  List<Integer> dividerIndexes=new ArrayList<Integer>();
  String content="";
  User user;
  for (int i=0; i < list.size(); i++) {
    user=list.get(i);
    dividerIndexes.add(content.length());
    content+=(i <= 0 ? "" : "?") + (user == null ? " " : user.getName());
  }
  dividerIndexes.add(content.length());
  SpannableString msp=new SpannableString(content + (count <= 9 ? " " : " ?????"));
  for (int i=0; i < dividerIndexes.size() - 1; i++) {
    setSpan(msp,i,dividerIndexes.get(i) + (i <= 0 ? 0 : DIVIDER_LENGTH),dividerIndexes.get(i + 1),list.get(i));
  }
  setText(msp);
  setMovementMethod(LinkMovementMethod.getInstance());
}
