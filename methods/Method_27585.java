@Override public void onSetCount(int count,boolean isOpen){
  if (isOpen) {
    open.setText(SpannableBuilder.builder().append(getString(R.string.open)).append("(").append(String.valueOf(count)).append(")"));
    close.setText(R.string.closed);
  }
 else {
    close.setText(SpannableBuilder.builder().append(getString(R.string.closed)).append("(").append(String.valueOf(count)).append(")"));
    open.setText(R.string.open);
  }
}
