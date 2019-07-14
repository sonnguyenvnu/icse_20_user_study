public static boolean isChannel(TLRPC.Dialog dialog){
  return dialog != null && (dialog.flags & 1) != 0;
}
