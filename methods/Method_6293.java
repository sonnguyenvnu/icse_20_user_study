public String getInviteText(int contacts){
  String link=inviteLink == null ? "https://telegram.org/dl" : inviteLink;
  if (contacts <= 1) {
    return LocaleController.formatString("InviteText2",R.string.InviteText2,link);
  }
 else {
    try {
      return String.format(LocaleController.getPluralString("InviteTextNum",contacts),contacts,link);
    }
 catch (    Exception e) {
      return LocaleController.formatString("InviteText2",R.string.InviteText2,link);
    }
  }
}
