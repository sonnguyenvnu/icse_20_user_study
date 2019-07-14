public void showRequestUrlAlert(final TLRPC.TL_urlAuthResultRequest request,TLRPC.TL_messages_requestUrlAuth buttonReq,String url){
  if (getParentActivity() == null) {
    return;
  }
  AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
  builder.setTitle(LocaleController.getString("OpenUrlTitle",R.string.OpenUrlTitle));
  String format=LocaleController.getString("OpenUrlAlert2",R.string.OpenUrlAlert2);
  int index=format.indexOf("%");
  SpannableStringBuilder stringBuilder=new SpannableStringBuilder(String.format(format,url));
  if (index >= 0) {
    stringBuilder.setSpan(new URLSpan(url),index,index + url.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
  }
  builder.setMessage(stringBuilder);
  builder.setMessageTextViewClickable(false);
  builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
  CheckBoxCell[] cells=new CheckBoxCell[2];
  LinearLayout linearLayout=new LinearLayout(getParentActivity());
  linearLayout.setOrientation(LinearLayout.VERTICAL);
  TLRPC.User selfUser=getUserConfig().getCurrentUser();
  for (int a=0; a < (request.request_write_access ? 2 : 1); a++) {
    cells[a]=new CheckBoxCell(getParentActivity(),1);
    cells[a].setBackgroundDrawable(Theme.getSelectorDrawable(false));
    cells[a].setMultiline(true);
    cells[a].setTag(a);
    if (a == 0) {
      stringBuilder=AndroidUtilities.replaceTags(LocaleController.formatString("OpenUrlOption1",R.string.OpenUrlOption1,request.domain,ContactsController.formatName(selfUser.first_name,selfUser.last_name)));
      index=TextUtils.indexOf(stringBuilder,request.domain);
      if (index >= 0) {
        stringBuilder.setSpan(new URLSpan(""),index,index + request.domain.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
      cells[a].setText(stringBuilder,"",true,false);
    }
 else     if (a == 1) {
      cells[a].setText(AndroidUtilities.replaceTags(LocaleController.formatString("OpenUrlOption2",R.string.OpenUrlOption2,UserObject.getFirstName(request.bot))),"",true,false);
    }
    cells[a].setPadding(LocaleController.isRTL ? AndroidUtilities.dp(16) : AndroidUtilities.dp(8),0,LocaleController.isRTL ? AndroidUtilities.dp(8) : AndroidUtilities.dp(16),0);
    linearLayout.addView(cells[a],LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    cells[a].setOnClickListener(v -> {
      if (!v.isEnabled()) {
        return;
      }
      Integer num=(Integer)v.getTag();
      cells[num].setChecked(!cells[num].isChecked(),true);
      if (num == 0 && cells[1] != null) {
        if (cells[num].isChecked()) {
          cells[1].setEnabled(true);
        }
 else {
          cells[1].setChecked(false,true);
          cells[1].setEnabled(false);
        }
      }
    }
);
  }
  builder.setCustomViewOffset(12);
  builder.setView(linearLayout);
  builder.setPositiveButton(LocaleController.getString("Open",R.string.Open),(dialogInterface,i) -> {
    if (!cells[0].isChecked()) {
      Browser.openUrl(getParentActivity(),url,false);
    }
 else {
      final AlertDialog[] progressDialog=new AlertDialog[]{new AlertDialog(getParentActivity(),3)};
      TLRPC.TL_messages_acceptUrlAuth req=new TLRPC.TL_messages_acceptUrlAuth();
      req.button_id=buttonReq.button_id;
      req.msg_id=buttonReq.msg_id;
      req.peer=buttonReq.peer;
      if (request.request_write_access) {
        req.write_allowed=cells[1].isChecked();
      }
      try {
        progressDialog[0].dismiss();
      }
 catch (      Throwable ignore) {
      }
      progressDialog[0]=null;
      int requestId=getConnectionsManager().sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
        if (response instanceof TLRPC.TL_urlAuthResultAccepted) {
          TLRPC.TL_urlAuthResultAccepted res=(TLRPC.TL_urlAuthResultAccepted)response;
          Browser.openUrl(getParentActivity(),res.url,false);
        }
 else         if (response instanceof TLRPC.TL_urlAuthResultDefault) {
          Browser.openUrl(getParentActivity(),url,false);
        }
      }
));
      AndroidUtilities.runOnUIThread(() -> {
        if (progressDialog[0] == null) {
          return;
        }
        progressDialog[0].setOnCancelListener(dialog -> ConnectionsManager.getInstance(currentAccount).cancelRequest(requestId,true));
        showDialog(progressDialog[0]);
      }
,500);
    }
  }
);
  showDialog(builder.create());
}
