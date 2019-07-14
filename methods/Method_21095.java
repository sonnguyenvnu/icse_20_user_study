@OnClick(R.id.terms_and_privacy) protected void termsAndPrivacyClicked(){
  final CharSequence[] items=new CharSequence[]{this.termsOfUseString,this.privacyPolicyString};
  new AlertDialog.Builder(this).setItems(items,(__,which) -> {
    final Intent intent;
    if (which == 0) {
      intent=new Intent(this,HelpActivity.Terms.class);
    }
 else {
      intent=new Intent(this,HelpActivity.Privacy.class);
    }
    startActivityWithTransition(intent,R.anim.slide_in_right,R.anim.fade_out_slide_out_left);
  }
).show();
}
