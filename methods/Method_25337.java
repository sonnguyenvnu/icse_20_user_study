private static String messageForFixes(Description description,List<AppliedFix> appliedFixes){
  StringBuilder messageBuilder=new StringBuilder(description.getMessage());
  boolean first=true;
  for (  AppliedFix appliedFix : appliedFixes) {
    if (first) {
      messageBuilder.append("\nDid you mean ");
    }
 else {
      messageBuilder.append(" or ");
    }
    if (appliedFix.isRemoveLine()) {
      messageBuilder.append("to remove this line");
    }
 else {
      messageBuilder.append("'").append(appliedFix.getNewCodeSnippet()).append("'");
    }
    first=false;
  }
  if (!first) {
    messageBuilder.append("?");
  }
  return messageBuilder.toString();
}
