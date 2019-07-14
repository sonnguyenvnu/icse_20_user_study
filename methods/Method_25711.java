private static boolean isApproximateMatchingComment(Comment comment,String formal){
switch (comment.getStyle()) {
case BLOCK:
case LINE:
    String commentText=Comments.getTextFromComment(comment);
  boolean textMatches=Arrays.asList(commentText.split("[^a-zA-Z0-9_]+",-1)).contains(formal);
boolean tooLong=commentText.length() > formal.length() + 5 && commentText.length() > 50;
boolean tooMuchMarkup=CharMatcher.anyOf("-*!@<>").countIn(commentText) > 5;
return textMatches && !tooLong && !tooMuchMarkup;
default :
return false;
}
}
