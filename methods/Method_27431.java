@Override public void bind(@NonNull CommitLinesModel item){
  leftLinNo.setText(item.getLeftLineNo() > 0 ? String.valueOf(item.getLeftLineNo()) : "  ");
  rightLinNo.setText(item.getRightLineNo() > 0 ? String.valueOf(item.getRightLineNo()) : "  ");
  hasComment.setVisibility(item.isHasCommentedOn() ? View.VISIBLE : View.GONE);
switch (item.getColor()) {
case CommitLinesModel.ADDITION:
    textView.setBackgroundColor(patchAdditionColor);
  break;
case CommitLinesModel.DELETION:
textView.setBackgroundColor(patchDeletionColor);
break;
case CommitLinesModel.PATCH:
leftLinNo.setVisibility(View.GONE);
rightLinNo.setVisibility(View.GONE);
textView.setBackgroundColor(patchRefColor);
break;
default :
textView.setBackgroundColor(Color.TRANSPARENT);
}
if (item.isNoNewLine()) {
textView.setText(SpannableBuilder.builder().append(item.getText()).append(" ").append(ContextCompat.getDrawable(textView.getContext(),R.drawable.ic_newline)));
}
 else {
textView.setText(item.getText());
}
}
