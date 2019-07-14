@Override public boolean isAvailable(){
  return channel.isOpen() && channel.isActive();
}
