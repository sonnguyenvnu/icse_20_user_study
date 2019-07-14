private void searchLinks(final CharSequence charSequence,final boolean force){
  if (currentEncryptedChat != null && (MessagesController.getInstance(currentAccount).secretWebpagePreview == 0 || AndroidUtilities.getPeerLayerVersion(currentEncryptedChat.layer) < 46)) {
    return;
  }
  if (force && foundWebPage != null) {
    if (foundWebPage.url != null) {
      int index=TextUtils.indexOf(charSequence,foundWebPage.url);
      char lastChar=0;
      boolean lenEqual=false;
      if (index == -1) {
        if (foundWebPage.display_url != null) {
          index=TextUtils.indexOf(charSequence,foundWebPage.display_url);
          lenEqual=index != -1 && index + foundWebPage.display_url.length() == charSequence.length();
          lastChar=index != -1 && !lenEqual ? charSequence.charAt(index + foundWebPage.display_url.length()) : 0;
        }
      }
 else {
        lenEqual=index + foundWebPage.url.length() == charSequence.length();
        lastChar=!lenEqual ? charSequence.charAt(index + foundWebPage.url.length()) : 0;
      }
      if (index != -1 && (lenEqual || lastChar == ' ' || lastChar == ',' || lastChar == '.' || lastChar == '!' || lastChar == '/')) {
        return;
      }
    }
    pendingLinkSearchString=null;
    foundUrls=null;
    showFieldPanelForWebPage(false,foundWebPage,false);
  }
  final MessagesController messagesController=MessagesController.getInstance(currentAccount);
  Utilities.searchQueue.postRunnable(() -> {
    if (linkSearchRequestId != 0) {
      ConnectionsManager.getInstance(currentAccount).cancelRequest(linkSearchRequestId,true);
      linkSearchRequestId=0;
    }
    ArrayList<CharSequence> urls=null;
    CharSequence textToCheck;
    try {
      Matcher m=AndroidUtilities.WEB_URL.matcher(charSequence);
      while (m.find()) {
        if (m.start() > 0) {
          if (charSequence.charAt(m.start() - 1) == '@') {
            continue;
          }
        }
        if (urls == null) {
          urls=new ArrayList<>();
        }
        urls.add(charSequence.subSequence(m.start(),m.end()));
      }
      if (charSequence instanceof Spannable) {
        URLSpanReplacement[] spans=((Spannable)charSequence).getSpans(0,charSequence.length(),URLSpanReplacement.class);
        if (spans != null && spans.length > 0) {
          if (urls == null) {
            urls=new ArrayList<>();
          }
          for (int a=0; a < spans.length; a++) {
            urls.add(spans[a].getURL());
          }
        }
      }
      if (urls != null && foundUrls != null && urls.size() == foundUrls.size()) {
        boolean clear=true;
        for (int a=0; a < urls.size(); a++) {
          if (!TextUtils.equals(urls.get(a),foundUrls.get(a))) {
            clear=false;
          }
        }
        if (clear) {
          return;
        }
      }
      foundUrls=urls;
      if (urls == null) {
        AndroidUtilities.runOnUIThread(() -> {
          if (foundWebPage != null) {
            showFieldPanelForWebPage(false,foundWebPage,false);
            foundWebPage=null;
          }
        }
);
        return;
      }
      textToCheck=TextUtils.join(" ",urls);
    }
 catch (    Exception e) {
      FileLog.e(e);
      String text=charSequence.toString().toLowerCase();
      if (charSequence.length() < 13 || !text.contains("http://") && !text.contains("https://")) {
        AndroidUtilities.runOnUIThread(() -> {
          if (foundWebPage != null) {
            showFieldPanelForWebPage(false,foundWebPage,false);
            foundWebPage=null;
          }
        }
);
        return;
      }
      textToCheck=charSequence;
    }
    if (currentEncryptedChat != null && messagesController.secretWebpagePreview == 2) {
      AndroidUtilities.runOnUIThread(() -> {
        AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
        builder.setTitle(LocaleController.getString("AppName",R.string.AppName));
        builder.setPositiveButton(LocaleController.getString("OK",R.string.OK),(dialog,which) -> {
          messagesController.secretWebpagePreview=1;
          MessagesController.getGlobalMainSettings().edit().putInt("secretWebpage2",MessagesController.getInstance(currentAccount).secretWebpagePreview).commit();
          foundUrls=null;
          searchLinks(charSequence,force);
        }
);
        builder.setNegativeButton(LocaleController.getString("Cancel",R.string.Cancel),null);
        builder.setMessage(LocaleController.getString("SecretLinkPreviewAlert",R.string.SecretLinkPreviewAlert));
        showDialog(builder.create());
        messagesController.secretWebpagePreview=0;
        MessagesController.getGlobalMainSettings().edit().putInt("secretWebpage2",messagesController.secretWebpagePreview).commit();
      }
);
      return;
    }
    final TLRPC.TL_messages_getWebPagePreview req=new TLRPC.TL_messages_getWebPagePreview();
    if (textToCheck instanceof String) {
      req.message=(String)textToCheck;
    }
 else {
      req.message=textToCheck.toString();
    }
    linkSearchRequestId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      linkSearchRequestId=0;
      if (error == null) {
        if (response instanceof TLRPC.TL_messageMediaWebPage) {
          foundWebPage=((TLRPC.TL_messageMediaWebPage)response).webpage;
          if (foundWebPage instanceof TLRPC.TL_webPage || foundWebPage instanceof TLRPC.TL_webPagePending) {
            if (foundWebPage instanceof TLRPC.TL_webPagePending) {
              pendingLinkSearchString=req.message;
            }
            if (currentEncryptedChat != null && foundWebPage instanceof TLRPC.TL_webPagePending) {
              foundWebPage.url=req.message;
            }
            showFieldPanelForWebPage(true,foundWebPage,false);
          }
 else {
            if (foundWebPage != null) {
              showFieldPanelForWebPage(false,foundWebPage,false);
              foundWebPage=null;
            }
          }
        }
 else {
          if (foundWebPage != null) {
            showFieldPanelForWebPage(false,foundWebPage,false);
            foundWebPage=null;
          }
        }
      }
    }
));
    ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(linkSearchRequestId,classGuid);
  }
);
}
