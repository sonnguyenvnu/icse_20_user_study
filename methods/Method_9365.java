private void createPhoneVerificationInterface(Context context){
  actionBar.setTitle(LocaleController.getString("PassportPhone",R.string.PassportPhone));
  FrameLayout frameLayout=new FrameLayout(context);
  scrollView.addView(frameLayout,LayoutHelper.createScroll(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,Gravity.TOP | Gravity.LEFT));
  for (int a=0; a < 3; a++) {
    views[a]=new PhoneConfirmationView(context,a + 2);
    views[a].setVisibility(View.GONE);
    frameLayout.addView(views[a],LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.TOP | Gravity.LEFT,AndroidUtilities.isTablet() ? 26 : 18,30,AndroidUtilities.isTablet() ? 26 : 18,0));
  }
  final Bundle params=new Bundle();
  params.putString("phone",currentValues.get("phone"));
  fillNextCodeParams(params,currentPhoneVerification,false);
}
