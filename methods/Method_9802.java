private String getAntiPlayer(String player){
  String anti=antiPlayer.get(player);
  if (null == anti || anti.equals("")) {
    for (    String temp : antiPlayer.keySet()) {
      if (player.equals(antiPlayer.get(temp))) {
        anti=temp;
      }
    }
  }
  return anti;
}
