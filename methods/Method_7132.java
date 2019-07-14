public void checkSecretHoles(TLRPC.EncryptedChat chat,ArrayList<TLRPC.Message> messages){
  ArrayList<TL_decryptedMessageHolder> holes=secretHolesQueue.get(chat.id);
  if (holes == null) {
    return;
  }
  Collections.sort(holes,(lhs,rhs) -> {
    if (lhs.layer.out_seq_no > rhs.layer.out_seq_no) {
      return 1;
    }
 else     if (lhs.layer.out_seq_no < rhs.layer.out_seq_no) {
      return -1;
    }
    return 0;
  }
);
  boolean update=false;
  for (int a=0; a < holes.size(); a++) {
    TL_decryptedMessageHolder holder=holes.get(a);
    if (holder.layer.out_seq_no == chat.seq_in || chat.seq_in == holder.layer.out_seq_no - 2) {
      applyPeerLayer(chat,holder.layer.layer);
      chat.seq_in=holder.layer.out_seq_no;
      chat.in_seq_no=holder.layer.in_seq_no;
      holes.remove(a);
      a--;
      update=true;
      if (holder.decryptedWithVersion == 2) {
        chat.mtproto_seq=Math.min(chat.mtproto_seq,chat.seq_in);
      }
      TLRPC.Message message=processDecryptedObject(chat,holder.file,holder.date,holder.layer.message,holder.new_key_used);
      if (message != null) {
        messages.add(message);
      }
    }
 else {
      break;
    }
  }
  if (holes.isEmpty()) {
    secretHolesQueue.remove(chat.id);
  }
  if (update) {
    MessagesStorage.getInstance(currentAccount).updateEncryptedChatSeq(chat,true);
  }
}
