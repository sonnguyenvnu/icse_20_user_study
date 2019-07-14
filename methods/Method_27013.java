@NonNull public static List<CommitLinesModel> getLines(@Nullable String text){
  ArrayList<CommitLinesModel> models=new ArrayList<>();
  if (!InputHelper.isEmpty(text)) {
    String[] split=text.split("\\r?\\n|\\r");
    if (split.length > 1) {
      int leftLineNo=-1;
      int rightLineNo=-1;
      int position=0;
      for (      String token : split) {
        char firstChar=token.charAt(0);
        boolean addLeft=false;
        boolean addRight=false;
        int color=TRANSPARENT;
        if (token.startsWith("@@")) {
          color=PATCH;
          Matcher matcher=HUNK_TITLE.matcher(token.trim());
          if (matcher.matches()) {
            try {
              leftLineNo=Math.abs(Integer.parseInt(matcher.group(1))) - 1;
              rightLineNo=Integer.parseInt(matcher.group(3)) - 1;
            }
 catch (            NumberFormatException e) {
              e.printStackTrace();
            }
          }
        }
 else         if (firstChar == '+') {
          position++;
          color=ADDITION;
          ++rightLineNo;
          addRight=true;
          addLeft=false;
        }
 else         if (firstChar == '-') {
          position++;
          color=DELETION;
          ++leftLineNo;
          addRight=false;
          addLeft=true;
        }
 else {
          position++;
          addLeft=true;
          addRight=true;
          ++rightLineNo;
          ++leftLineNo;
        }
        int index=token.indexOf("\\ No newline at end of file");
        if (index != -1) {
          token=token.replace("\\ No newline at end of file","");
        }
        models.add(new CommitLinesModel(token,color,token.startsWith("@@") || !addLeft ? -1 : leftLineNo,token.startsWith("@@") || !addRight ? -1 : rightLineNo,index != -1,position,false));
      }
    }
  }
  return models;
}
