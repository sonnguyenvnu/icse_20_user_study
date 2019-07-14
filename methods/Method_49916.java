private void broadcastProgressIfNeeded(int progress){
  if (mToken > 0) {
    Intent intent=new Intent(PROGRESS_STATUS_ACTION);
    intent.putExtra("progress",progress);
    intent.putExtra("token",mToken);
    BroadcastUtils.sendExplicitBroadcast(mContext,intent,PROGRESS_STATUS_ACTION);
  }
}
