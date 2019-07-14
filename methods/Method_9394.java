private void deleteValueInternal(final TLRPC.TL_secureRequiredType requiredType,final TLRPC.TL_secureRequiredType documentRequiredType,final ArrayList<TLRPC.TL_secureRequiredType> documentRequiredTypes,final boolean deleteType,final Runnable finishRunnable,final ErrorRunnable errorRunnable,boolean documentOnly){
  if (requiredType == null) {
    return;
  }
  TLRPC.TL_account_deleteSecureValue req=new TLRPC.TL_account_deleteSecureValue();
  if (documentOnly && documentRequiredType != null) {
    req.types.add(documentRequiredType.type);
  }
 else {
    if (deleteType) {
      req.types.add(requiredType.type);
    }
    if (documentRequiredType != null) {
      req.types.add(documentRequiredType.type);
    }
  }
  ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
    if (error != null) {
      if (errorRunnable != null) {
        errorRunnable.onError(error.text,null);
      }
      showAlertWithText(LocaleController.getString("AppName",R.string.AppName),error.text);
    }
 else {
      if (documentOnly) {
        if (documentRequiredType != null) {
          removeValue(documentRequiredType);
        }
 else {
          removeValue(requiredType);
        }
      }
 else {
        if (deleteType) {
          removeValue(requiredType);
        }
        removeValue(documentRequiredType);
      }
      if (currentActivityType == TYPE_MANAGE) {
        TextDetailSecureCell view=typesViews.remove(requiredType);
        if (view != null) {
          linearLayout2.removeView(view);
          View child=linearLayout2.getChildAt(linearLayout2.getChildCount() - 6);
          if (child instanceof TextDetailSecureCell) {
            ((TextDetailSecureCell)child).setNeedDivider(false);
          }
        }
        updateManageVisibility();
      }
 else {
        String documentJson=null;
        TLRPC.TL_secureRequiredType documentsType=documentRequiredType;
        if (documentsType != null && documentRequiredTypes != null && documentRequiredTypes.size() > 1) {
          for (int a=0, count=documentRequiredTypes.size(); a < count; a++) {
            TLRPC.TL_secureRequiredType documentType=documentRequiredTypes.get(a);
            TLRPC.TL_secureValue documentValue=getValueByType(documentType,false);
            if (documentValue != null) {
              if (documentValue.data != null) {
                documentJson=decryptData(documentValue.data.data,decryptValueSecret(documentValue.data.secret,documentValue.data.data_hash),documentValue.data.data_hash);
              }
              documentsType=documentType;
              break;
            }
          }
          if (documentsType == null) {
            documentsType=documentRequiredTypes.get(0);
          }
        }
        if (deleteType) {
          setTypeValue(requiredType,null,null,documentsType,documentJson,documentOnly,documentRequiredTypes != null ? documentRequiredTypes.size() : 0);
        }
 else {
          String json=null;
          TLRPC.TL_secureValue value=getValueByType(requiredType,false);
          if (value != null && value.data != null) {
            json=decryptData(value.data.data,decryptValueSecret(value.data.secret,value.data.data_hash),value.data.data_hash);
          }
          setTypeValue(requiredType,null,json,documentsType,documentJson,documentOnly,documentRequiredTypes != null ? documentRequiredTypes.size() : 0);
        }
      }
      if (finishRunnable != null) {
        finishRunnable.run();
      }
    }
  }
));
}
