@Override public void bind(TimelineItem.Diff item){
  ReviewComment comment=item.getInitialComment();
  mFileTextView.setTag(item.getInitialTimelineComment());
  mFileTextView.setText(comment.path());
  boolean isOutdated=comment.position() == null;
  mFileTextView.setPaintFlags(isOutdated ? mFileTextView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG : mFileTextView.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
  mFileTextView.setClickable(!isOutdated);
  mFileTextView.setTextColor(isOutdated ? mSecondaryTextColor : mAccentColor);
  String[] lines=comment.diffChunk().split("\n");
  int leftLine=0;
  int rightLine=0;
  int[] lineNumbers=StringUtils.extractDiffHunkLineNumbers(lines[0]);
  if (lineNumbers != null) {
    leftLine=lineNumbers[0];
    rightLine=lineNumbers[1];
  }
  int maxLine=Math.max(rightLine,leftLine) + lines.length;
  int maxLineLength=String.valueOf(maxLine).length();
  SpannableStringBuilder builder=new SpannableStringBuilder();
  int start=Math.max(1,lines.length - 4);
  for (int i=1; i < lines.length; i++) {
    boolean isLeftLine=false;
    boolean isRightLine=false;
    if (lines[i].startsWith("-")) {
      leftLine+=1;
      isLeftLine=true;
    }
 else     if (lines[i].startsWith("+")) {
      rightLine+=1;
      isRightLine=true;
    }
 else {
      leftLine+=1;
      rightLine+=1;
    }
    if (i < start) {
      continue;
    }
    int spanStart=builder.length();
    String leftLineText=!isRightLine && leftLine > 0 ? String.valueOf(leftLine) : "";
    appendLineNumber(builder,maxLineLength,leftLineText,leftLine,item,false);
    String rightLineText=!isLeftLine && rightLine > 0 ? String.valueOf(rightLine) : "";
    appendLineNumber(builder,maxLineLength,rightLineText,rightLine,item,true);
    builder.append(" ");
    int lineNumberLength=builder.length() - spanStart;
    builder.append(" ").append(lines[i]).append(" ");
    if (i < lines.length - 1) {
      builder.append("\n");
    }
    int backgroundColor=mDefaultBackgroundColor;
    int lineNumberBackgroundColor=mDefaultLineNumberBackgroundColor;
    if (lines[i].startsWith("+")) {
      backgroundColor=mAddedLineBackgroundColor;
      lineNumberBackgroundColor=mAddedLineNumberBackgroundColor;
    }
 else     if (lines[i].startsWith("-")) {
      backgroundColor=mRemovedLineBackgroundColor;
      lineNumberBackgroundColor=mRemovedLineNumberBackgroundColor;
    }
    DiffLineSpan span=new DiffLineSpan(backgroundColor,lineNumberBackgroundColor,mPadding,i == start,i == lines.length - 1,lineNumberLength);
    builder.setSpan(span,spanStart,builder.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  mDiffHunkTextView.setText(builder);
  mDiffHunkTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX,mInitialDiffTextSize * getDiffSizeMultiplier());
}
