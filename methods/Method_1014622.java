@Override public void handle(OnlineUser user,JSONObject json){
  if (!user.getUser().isGuest()) {
    if (json.get("t").equals("k")) {
      int key=(int)(long)json.get("k");
      ArrayList<Integer> buffer=user.getUser().getControlledUnit().getKeyboardBuffer();
      if (buffer.size() < 16) {
        buffer.add(key);
      }
    }
  }
}
