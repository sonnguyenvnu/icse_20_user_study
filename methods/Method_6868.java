private void updatePrintingStrings(){
  final LongSparseArray<CharSequence> newPrintingStrings=new LongSparseArray<>();
  final LongSparseArray<Integer> newPrintingStringsTypes=new LongSparseArray<>();
  for (  HashMap.Entry<Long,ArrayList<PrintingUser>> entry : printingUsers.entrySet()) {
    long key=entry.getKey();
    ArrayList<PrintingUser> arr=entry.getValue();
    int lower_id=(int)key;
    if (lower_id > 0 || lower_id == 0 || arr.size() == 1) {
      PrintingUser pu=arr.get(0);
      TLRPC.User user=getUser(pu.userId);
      if (user == null) {
        continue;
      }
      if (pu.action instanceof TLRPC.TL_sendMessageRecordAudioAction) {
        if (lower_id < 0) {
          newPrintingStrings.put(key,LocaleController.formatString("IsRecordingAudio",R.string.IsRecordingAudio,getUserNameForTyping(user)));
        }
 else {
          newPrintingStrings.put(key,LocaleController.getString("RecordingAudio",R.string.RecordingAudio));
        }
        newPrintingStringsTypes.put(key,1);
      }
 else       if (pu.action instanceof TLRPC.TL_sendMessageRecordRoundAction || pu.action instanceof TLRPC.TL_sendMessageUploadRoundAction) {
        if (lower_id < 0) {
          newPrintingStrings.put(key,LocaleController.formatString("IsRecordingRound",R.string.IsRecordingRound,getUserNameForTyping(user)));
        }
 else {
          newPrintingStrings.put(key,LocaleController.getString("RecordingRound",R.string.RecordingRound));
        }
        newPrintingStringsTypes.put(key,4);
      }
 else       if (pu.action instanceof TLRPC.TL_sendMessageUploadAudioAction) {
        if (lower_id < 0) {
          newPrintingStrings.put(key,LocaleController.formatString("IsSendingAudio",R.string.IsSendingAudio,getUserNameForTyping(user)));
        }
 else {
          newPrintingStrings.put(key,LocaleController.getString("SendingAudio",R.string.SendingAudio));
        }
        newPrintingStringsTypes.put(key,2);
      }
 else       if (pu.action instanceof TLRPC.TL_sendMessageUploadVideoAction || pu.action instanceof TLRPC.TL_sendMessageRecordVideoAction) {
        if (lower_id < 0) {
          newPrintingStrings.put(key,LocaleController.formatString("IsSendingVideo",R.string.IsSendingVideo,getUserNameForTyping(user)));
        }
 else {
          newPrintingStrings.put(key,LocaleController.getString("SendingVideoStatus",R.string.SendingVideoStatus));
        }
        newPrintingStringsTypes.put(key,2);
      }
 else       if (pu.action instanceof TLRPC.TL_sendMessageUploadDocumentAction) {
        if (lower_id < 0) {
          newPrintingStrings.put(key,LocaleController.formatString("IsSendingFile",R.string.IsSendingFile,getUserNameForTyping(user)));
        }
 else {
          newPrintingStrings.put(key,LocaleController.getString("SendingFile",R.string.SendingFile));
        }
        newPrintingStringsTypes.put(key,2);
      }
 else       if (pu.action instanceof TLRPC.TL_sendMessageUploadPhotoAction) {
        if (lower_id < 0) {
          newPrintingStrings.put(key,LocaleController.formatString("IsSendingPhoto",R.string.IsSendingPhoto,getUserNameForTyping(user)));
        }
 else {
          newPrintingStrings.put(key,LocaleController.getString("SendingPhoto",R.string.SendingPhoto));
        }
        newPrintingStringsTypes.put(key,2);
      }
 else       if (pu.action instanceof TLRPC.TL_sendMessageGamePlayAction) {
        if (lower_id < 0) {
          newPrintingStrings.put(key,LocaleController.formatString("IsSendingGame",R.string.IsSendingGame,getUserNameForTyping(user)));
        }
 else {
          newPrintingStrings.put(key,LocaleController.getString("SendingGame",R.string.SendingGame));
        }
        newPrintingStringsTypes.put(key,3);
      }
 else {
        if (lower_id < 0) {
          newPrintingStrings.put(key,LocaleController.formatString("IsTypingGroup",R.string.IsTypingGroup,getUserNameForTyping(user)));
        }
 else {
          newPrintingStrings.put(key,LocaleController.getString("Typing",R.string.Typing));
        }
        newPrintingStringsTypes.put(key,0);
      }
    }
 else {
      int count=0;
      StringBuilder label=new StringBuilder();
      for (      PrintingUser pu : arr) {
        TLRPC.User user=getUser(pu.userId);
        if (user != null) {
          if (label.length() != 0) {
            label.append(", ");
          }
          label.append(getUserNameForTyping(user));
          count++;
        }
        if (count == 2) {
          break;
        }
      }
      if (label.length() != 0) {
        if (count == 1) {
          newPrintingStrings.put(key,LocaleController.formatString("IsTypingGroup",R.string.IsTypingGroup,label.toString()));
        }
 else {
          if (arr.size() > 2) {
            String plural=LocaleController.getPluralString("AndMoreTypingGroup",arr.size() - 2);
            try {
              newPrintingStrings.put(key,String.format(plural,label.toString(),arr.size() - 2));
            }
 catch (            Exception e) {
              newPrintingStrings.put(key,"LOC_ERR: AndMoreTypingGroup");
            }
          }
 else {
            newPrintingStrings.put(key,LocaleController.formatString("AreTypingGroup",R.string.AreTypingGroup,label.toString()));
          }
        }
        newPrintingStringsTypes.put(key,0);
      }
    }
  }
  lastPrintingStringCount=newPrintingStrings.size();
  AndroidUtilities.runOnUIThread(() -> {
    printingStrings=newPrintingStrings;
    printingStringsTypes=newPrintingStringsTypes;
  }
);
}
