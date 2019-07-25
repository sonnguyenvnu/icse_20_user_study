@Override public void disconnected(ChannelWrapper channel) throws Exception {
  user.getPendingConnects().remove(target);
}
