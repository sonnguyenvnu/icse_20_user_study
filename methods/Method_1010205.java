public void blocked(boolean blocked){
  notify(String.format("[blocked:%b]",blocked).getBytes());
}
