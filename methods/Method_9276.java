private void updateHint(){
  if (selectedContacts.isEmpty()) {
    infoTextView.setVisibility(View.VISIBLE);
    counterView.setVisibility(View.INVISIBLE);
  }
 else {
    infoTextView.setVisibility(View.INVISIBLE);
    counterView.setVisibility(View.VISIBLE);
    counterTextView.setText(String.format("%d",selectedContacts.size()));
  }
}
