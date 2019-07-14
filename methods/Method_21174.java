private void showConfirmGamesNewsletterDialog(){
  final String optInDialogMessageString=this.ksString.format(this.optInMessageString,"newsletter",this.newsletterGamesString);
  final AlertDialog.Builder builder=new AlertDialog.Builder(this).setMessage(optInDialogMessageString).setTitle(this.optInTitleString).setPositiveButton(this.okString,(__,___) -> {
  }
);
  builder.show();
}
