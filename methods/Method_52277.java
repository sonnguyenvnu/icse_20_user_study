private String errorMsgFor(List<String> badWords){
  StringBuilder msg=new StringBuilder(this.getMessage()).append(": ");
  if (badWords.size() == 1) {
    msg.append("Invalid term: '").append(badWords.get(0)).append('\'');
  }
 else {
    msg.append("Invalid terms: '");
    msg.append(badWords.get(0));
    for (int i=1; i < badWords.size(); i++) {
      msg.append("', '").append(badWords.get(i));
    }
    msg.append('\'');
  }
  return msg.toString();
}
