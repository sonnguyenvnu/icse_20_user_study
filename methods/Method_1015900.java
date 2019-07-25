@Override public void exception(Throwable t) throws Exception {
  if (obsolete) {
    return;
  }
  String message="Exception Connecting:" + Util.exception(t);
  if (user.getServer() == null) {
    user.disconnect(message);
  }
 else {
    user.sendMessage(ChatColor.RED + message);
  }
}
