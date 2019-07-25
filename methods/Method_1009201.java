@Override public void apply(@NotNull PsiFile file,@NotNull List<Issue> issueList,@NotNull AnnotationHolder holder){
  if (issueList.size() > 0) {
    @Nullable Document document=file.getViewProvider().getDocument();
    if (document != null) {
      String workingDirectory=ensureWorkingDirectory(file.getProject());
      for (      Issue issue : issueList) {
        int lineStartOffset=document.getLineStartOffset(issue.line);
        int start;
        int end;
        if (issue.column != null) {
          start=lineStartOffset + issue.column;
          end=start + 1;
        }
 else {
          start=lineStartOffset;
          end=document.getLineEndOffset(issue.line);
        }
        Annotation annotation=holder.createWarningAnnotation(new TextRange(start,end),issue.message);
        annotation.setAfterEndOfLine(end == start);
        issue.explanation.ifPresent(explanation -> {
          String toolTip=explanationToToolTip(explanation,workingDirectory);
          if (!toolTip.isEmpty()) {
            annotation.setTooltip(toolTip);
          }
        }
);
      }
    }
  }
}
