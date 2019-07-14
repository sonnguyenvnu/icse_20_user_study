public static void createReportAlert(final Context context,final long dialog_id,final int messageId,final BaseFragment parentFragment){
  if (context == null || parentFragment == null) {
    return;
  }
  BottomSheet.Builder builder=new BottomSheet.Builder(context);
  builder.setTitle(LocaleController.getString("ReportChat",R.string.ReportChat));
  CharSequence[] items=new CharSequence[]{LocaleController.getString("ReportChatSpam",R.string.ReportChatSpam),LocaleController.getString("ReportChatViolence",R.string.ReportChatViolence),LocaleController.getString("ReportChatChild",R.string.ReportChatChild),LocaleController.getString("ReportChatPornography",R.string.ReportChatPornography),LocaleController.getString("ReportChatOther",R.string.ReportChatOther)};
  builder.setItems(items,(dialogInterface,i) -> {
    if (i == 4) {
      Bundle args=new Bundle();
      args.putLong("dialog_id",dialog_id);
      args.putLong("message_id",messageId);
      parentFragment.presentFragment(new ReportOtherActivity(args));
      return;
    }
    TLObject req;
    TLRPC.InputPeer peer=MessagesController.getInstance(UserConfig.selectedAccount).getInputPeer((int)dialog_id);
    if (messageId != 0) {
      TLRPC.TL_messages_report request=new TLRPC.TL_messages_report();
      request.peer=peer;
      request.id.add(messageId);
      if (i == 0) {
        request.reason=new TLRPC.TL_inputReportReasonSpam();
      }
 else       if (i == 1) {
        request.reason=new TLRPC.TL_inputReportReasonViolence();
      }
 else       if (i == 2) {
        request.reason=new TLRPC.TL_inputReportReasonChildAbuse();
      }
 else       if (i == 3) {
        request.reason=new TLRPC.TL_inputReportReasonPornography();
      }
      req=request;
    }
 else {
      TLRPC.TL_account_reportPeer request=new TLRPC.TL_account_reportPeer();
      request.peer=peer;
      if (i == 0) {
        request.reason=new TLRPC.TL_inputReportReasonSpam();
      }
 else       if (i == 1) {
        request.reason=new TLRPC.TL_inputReportReasonViolence();
      }
 else       if (i == 2) {
        request.reason=new TLRPC.TL_inputReportReasonChildAbuse();
      }
 else       if (i == 3) {
        request.reason=new TLRPC.TL_inputReportReasonPornography();
      }
      req=request;
    }
    ConnectionsManager.getInstance(UserConfig.selectedAccount).sendRequest(req,(response,error) -> {
    }
);
    Toast.makeText(context,LocaleController.getString("ReportChatSent",R.string.ReportChatSent),Toast.LENGTH_SHORT).show();
  }
);
  BottomSheet sheet=builder.create();
  parentFragment.showDialog(sheet);
}
