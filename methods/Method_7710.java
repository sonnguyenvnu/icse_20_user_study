public static void reloadAllResources(Context context){
  destroyResources();
  if (chat_msgInDrawable != null) {
    chat_msgInDrawable=null;
    currentColor=0;
    currentSelectedColor=0;
    createChatResources(context,false);
  }
  if (dialogs_namePaint != null) {
    dialogs_namePaint=null;
    createDialogsResources(context);
  }
  if (profile_verifiedDrawable != null) {
    profile_verifiedDrawable=null;
    createProfileResources(context);
  }
}
