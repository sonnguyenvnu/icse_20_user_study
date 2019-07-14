public boolean isChannelAdmin(int chatId,int uid){
  ArrayList<Integer> array=channelAdmins.get(chatId);
  return array != null && array.indexOf(uid) >= 0;
}
