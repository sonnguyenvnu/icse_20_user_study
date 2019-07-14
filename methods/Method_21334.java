private void setStyledNameAndBlurb(final @NonNull Pair<String,String> nameAndBlurb){
  final String nameString=ProjectUtils.isProjectNamePunctuated(nameAndBlurb.first) ? nameAndBlurb.first + " " : nameAndBlurb.first + ": ";
  final String blurbString=nameAndBlurb.second;
  final SpannableString styledString=new SpannableString(nameString + blurbString);
  styledString.setSpan(new ForegroundColorSpan(this.ksrSoftBlack),0,nameString.length(),0);
  styledString.setSpan(new ForegroundColorSpan(this.ksrDarkGrey400),nameString.length(),nameString.length() + blurbString.length(),0);
  this.nameAndBlurbTextView.setText(styledString);
}
