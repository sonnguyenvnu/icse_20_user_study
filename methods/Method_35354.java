public void updateSummaryText(){
  if (textViewEndSummary != null) {
    textViewEndSummary.setText(getEndSummaryText(super.getItemCount()));
  }
}
