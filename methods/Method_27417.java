private static void registerClickEvent(@NonNull TextView textView){
  BetterLinkMovementExtended betterLinkMovementMethod=BetterLinkMovementExtended.linkifyHtml(textView);
  betterLinkMovementMethod.setOnLinkClickListener((view,url) -> {
    SchemeParser.launchUri(view.getContext(),Uri.parse(url));
    return true;
  }
);
  betterLinkMovementMethod.setOnLinkLongClickListener((view,url) -> {
    view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
    PopupMenu menu=new PopupMenu(view.getContext(),view);
    menu.setOnMenuItemClickListener(menuItem -> {
switch (menuItem.getItemId()) {
case R.id.copy:
        AppHelper.copyToClipboard(view.getContext(),url);
      return true;
case R.id.open:
    SchemeParser.launchUri(view.getContext(),Uri.parse(url));
  return true;
case R.id.open_new_window:
SchemeParser.launchUri(view.getContext(),Uri.parse(url),false,true);
return true;
default :
return false;
}
}
);
menu.inflate(R.menu.link_popup_menu);
menu.show();
return true;
}
);
}
