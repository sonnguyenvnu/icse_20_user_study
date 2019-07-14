public static void destroyResources(){
  for (int a=0; a < chat_attachButtonDrawables.length; a++) {
    if (chat_attachButtonDrawables[a] != null) {
      chat_attachButtonDrawables[a].setCallback(null);
    }
  }
}
