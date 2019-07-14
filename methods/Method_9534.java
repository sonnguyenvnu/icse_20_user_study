@Override protected void onTransitionAnimationEnd(boolean isOpen,boolean backward){
  if (isOpen) {
    AndroidUtilities.runOnUIThread(() -> {
      if (listView != null) {
        RecyclerView.ViewHolder holder=listView.findViewHolderForAdapterPosition(questionRow);
        if (holder != null) {
          PollEditTextCell textCell=(PollEditTextCell)holder.itemView;
          EditTextBoldCursor editText=textCell.getTextView();
          editText.requestFocus();
          AndroidUtilities.showKeyboard(editText);
        }
      }
    }
,100);
  }
}
