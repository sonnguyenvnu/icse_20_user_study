private void resetItems(){
  accountNumbers.clear();
  for (int a=0; a < UserConfig.MAX_ACCOUNT_COUNT; a++) {
    if (UserConfig.getInstance(a).isClientActivated()) {
      accountNumbers.add(a);
    }
  }
  Collections.sort(accountNumbers,(o1,o2) -> {
    long l1=UserConfig.getInstance(o1).loginTime;
    long l2=UserConfig.getInstance(o2).loginTime;
    if (l1 > l2) {
      return 1;
    }
 else     if (l1 < l2) {
      return -1;
    }
    return 0;
  }
);
  items.clear();
  if (!UserConfig.getInstance(UserConfig.selectedAccount).isClientActivated()) {
    return;
  }
  int eventType=Theme.getEventType();
  if (eventType == 0) {
    items.add(new Item(2,LocaleController.getString("NewGroup",R.string.NewGroup),R.drawable.menu_groups_ny));
    items.add(new Item(3,LocaleController.getString("NewSecretChat",R.string.NewSecretChat),R.drawable.menu_secret_ny));
    items.add(new Item(4,LocaleController.getString("NewChannel",R.string.NewChannel),R.drawable.menu_channel_ny));
    items.add(null);
    items.add(new Item(6,LocaleController.getString("Contacts",R.string.Contacts),R.drawable.menu_contacts_ny));
    items.add(new Item(11,LocaleController.getString("SavedMessages",R.string.SavedMessages),R.drawable.menu_bookmarks_ny));
    items.add(new Item(10,LocaleController.getString("Calls",R.string.Calls),R.drawable.menu_calls_ny));
    items.add(new Item(7,LocaleController.getString("InviteFriends",R.string.InviteFriends),R.drawable.menu_invite_ny));
    items.add(new Item(8,LocaleController.getString("Settings",R.string.Settings),R.drawable.menu_settings_ny));
    items.add(new Item(9,LocaleController.getString("TelegramFAQ",R.string.TelegramFAQ),R.drawable.menu_help_ny));
  }
 else {
    items.add(new Item(2,LocaleController.getString("NewGroup",R.string.NewGroup),R.drawable.menu_groups));
    items.add(new Item(3,LocaleController.getString("NewSecretChat",R.string.NewSecretChat),R.drawable.menu_secret));
    items.add(new Item(4,LocaleController.getString("NewChannel",R.string.NewChannel),R.drawable.menu_broadcast));
    items.add(null);
    items.add(new Item(6,LocaleController.getString("Contacts",R.string.Contacts),R.drawable.menu_contacts));
    items.add(new Item(11,LocaleController.getString("SavedMessages",R.string.SavedMessages),R.drawable.menu_saved));
    items.add(new Item(10,LocaleController.getString("Calls",R.string.Calls),R.drawable.menu_calls));
    items.add(new Item(7,LocaleController.getString("InviteFriends",R.string.InviteFriends),R.drawable.menu_invite));
    items.add(new Item(8,LocaleController.getString("Settings",R.string.Settings),R.drawable.menu_settings));
    items.add(new Item(9,LocaleController.getString("TelegramFAQ",R.string.TelegramFAQ),R.drawable.menu_help));
  }
}
