public TLRPC.Message processDecryptedObject(final TLRPC.EncryptedChat chat,final TLRPC.EncryptedFile file,int date,TLObject object,boolean new_key_used){
  if (object != null) {
    int from_id=chat.admin_id;
    if (from_id == UserConfig.getInstance(currentAccount).getClientUserId()) {
      from_id=chat.participant_id;
    }
    if (AndroidUtilities.getPeerLayerVersion(chat.layer) >= 20 && chat.exchange_id == 0 && chat.future_key_fingerprint == 0 && chat.key_use_count_in >= 120) {
      requestNewSecretChatKey(chat);
    }
    if (chat.exchange_id == 0 && chat.future_key_fingerprint != 0 && !new_key_used) {
      chat.future_auth_key=new byte[256];
      chat.future_key_fingerprint=0;
      MessagesStorage.getInstance(currentAccount).updateEncryptedChat(chat);
    }
 else     if (chat.exchange_id != 0 && new_key_used) {
      chat.key_fingerprint=chat.future_key_fingerprint;
      chat.auth_key=chat.future_auth_key;
      chat.key_create_date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
      chat.future_auth_key=new byte[256];
      chat.future_key_fingerprint=0;
      chat.key_use_count_in=0;
      chat.key_use_count_out=0;
      chat.exchange_id=0;
      MessagesStorage.getInstance(currentAccount).updateEncryptedChat(chat);
    }
    if (object instanceof TLRPC.TL_decryptedMessage) {
      TLRPC.TL_decryptedMessage decryptedMessage=(TLRPC.TL_decryptedMessage)object;
      TLRPC.TL_message newMessage;
      if (AndroidUtilities.getPeerLayerVersion(chat.layer) >= 17) {
        newMessage=new TLRPC.TL_message_secret();
        newMessage.ttl=decryptedMessage.ttl;
        newMessage.entities=decryptedMessage.entities;
      }
 else {
        newMessage=new TLRPC.TL_message();
        newMessage.ttl=chat.ttl;
      }
      newMessage.message=decryptedMessage.message;
      newMessage.date=date;
      newMessage.local_id=newMessage.id=UserConfig.getInstance(currentAccount).getNewMessageId();
      UserConfig.getInstance(currentAccount).saveConfig(false);
      newMessage.from_id=from_id;
      newMessage.to_id=new TLRPC.TL_peerUser();
      newMessage.random_id=decryptedMessage.random_id;
      newMessage.to_id.user_id=UserConfig.getInstance(currentAccount).getClientUserId();
      newMessage.unread=true;
      newMessage.flags=TLRPC.MESSAGE_FLAG_HAS_MEDIA | TLRPC.MESSAGE_FLAG_HAS_FROM_ID;
      if (decryptedMessage.via_bot_name != null && decryptedMessage.via_bot_name.length() > 0) {
        newMessage.via_bot_name=decryptedMessage.via_bot_name;
        newMessage.flags|=TLRPC.MESSAGE_FLAG_HAS_BOT_ID;
      }
      if (decryptedMessage.grouped_id != 0) {
        newMessage.grouped_id=decryptedMessage.grouped_id;
        newMessage.flags|=131072;
      }
      newMessage.dialog_id=((long)chat.id) << 32;
      if (decryptedMessage.reply_to_random_id != 0) {
        newMessage.reply_to_random_id=decryptedMessage.reply_to_random_id;
        newMessage.flags|=TLRPC.MESSAGE_FLAG_REPLY;
      }
      if (decryptedMessage.media == null || decryptedMessage.media instanceof TLRPC.TL_decryptedMessageMediaEmpty) {
        newMessage.media=new TLRPC.TL_messageMediaEmpty();
      }
 else       if (decryptedMessage.media instanceof TLRPC.TL_decryptedMessageMediaWebPage) {
        newMessage.media=new TLRPC.TL_messageMediaWebPage();
        newMessage.media.webpage=new TLRPC.TL_webPageUrlPending();
        newMessage.media.webpage.url=decryptedMessage.media.url;
      }
 else       if (decryptedMessage.media instanceof TLRPC.TL_decryptedMessageMediaContact) {
        newMessage.media=new TLRPC.TL_messageMediaContact();
        newMessage.media.last_name=decryptedMessage.media.last_name;
        newMessage.media.first_name=decryptedMessage.media.first_name;
        newMessage.media.phone_number=decryptedMessage.media.phone_number;
        newMessage.media.user_id=decryptedMessage.media.user_id;
        newMessage.media.vcard="";
      }
 else       if (decryptedMessage.media instanceof TLRPC.TL_decryptedMessageMediaGeoPoint) {
        newMessage.media=new TLRPC.TL_messageMediaGeo();
        newMessage.media.geo=new TLRPC.TL_geoPoint();
        newMessage.media.geo.lat=decryptedMessage.media.lat;
        newMessage.media.geo._long=decryptedMessage.media._long;
      }
 else       if (decryptedMessage.media instanceof TLRPC.TL_decryptedMessageMediaPhoto) {
        if (decryptedMessage.media.key == null || decryptedMessage.media.key.length != 32 || decryptedMessage.media.iv == null || decryptedMessage.media.iv.length != 32) {
          return null;
        }
        newMessage.media=new TLRPC.TL_messageMediaPhoto();
        newMessage.media.flags|=3;
        newMessage.message=decryptedMessage.media.caption != null ? decryptedMessage.media.caption : "";
        newMessage.media.photo=new TLRPC.TL_photo();
        newMessage.media.photo.file_reference=new byte[0];
        newMessage.media.photo.date=newMessage.date;
        byte[] thumb=((TLRPC.TL_decryptedMessageMediaPhoto)decryptedMessage.media).thumb;
        if (thumb != null && thumb.length != 0 && thumb.length <= 6000 && decryptedMessage.media.thumb_w <= 100 && decryptedMessage.media.thumb_h <= 100) {
          TLRPC.TL_photoCachedSize small=new TLRPC.TL_photoCachedSize();
          small.w=decryptedMessage.media.thumb_w;
          small.h=decryptedMessage.media.thumb_h;
          small.bytes=thumb;
          small.type="s";
          small.location=new TLRPC.TL_fileLocationUnavailable();
          newMessage.media.photo.sizes.add(small);
        }
        if (newMessage.ttl != 0) {
          newMessage.media.ttl_seconds=newMessage.ttl;
          newMessage.media.flags|=4;
        }
        TLRPC.TL_photoSize big=new TLRPC.TL_photoSize();
        big.w=decryptedMessage.media.w;
        big.h=decryptedMessage.media.h;
        big.type="x";
        big.size=file.size;
        big.location=new TLRPC.TL_fileEncryptedLocation();
        big.location.key=decryptedMessage.media.key;
        big.location.iv=decryptedMessage.media.iv;
        big.location.dc_id=file.dc_id;
        big.location.volume_id=file.id;
        big.location.secret=file.access_hash;
        big.location.local_id=file.key_fingerprint;
        newMessage.media.photo.sizes.add(big);
      }
 else       if (decryptedMessage.media instanceof TLRPC.TL_decryptedMessageMediaVideo) {
        if (decryptedMessage.media.key == null || decryptedMessage.media.key.length != 32 || decryptedMessage.media.iv == null || decryptedMessage.media.iv.length != 32) {
          return null;
        }
        newMessage.media=new TLRPC.TL_messageMediaDocument();
        newMessage.media.flags|=3;
        newMessage.media.document=new TLRPC.TL_documentEncrypted();
        newMessage.media.document.key=decryptedMessage.media.key;
        newMessage.media.document.iv=decryptedMessage.media.iv;
        newMessage.media.document.dc_id=file.dc_id;
        newMessage.message=decryptedMessage.media.caption != null ? decryptedMessage.media.caption : "";
        newMessage.media.document.date=date;
        newMessage.media.document.size=file.size;
        newMessage.media.document.id=file.id;
        newMessage.media.document.access_hash=file.access_hash;
        newMessage.media.document.mime_type=decryptedMessage.media.mime_type;
        if (newMessage.media.document.mime_type == null) {
          newMessage.media.document.mime_type="video/mp4";
        }
        byte[] thumb=((TLRPC.TL_decryptedMessageMediaVideo)decryptedMessage.media).thumb;
        TLRPC.PhotoSize photoSize;
        if (thumb != null && thumb.length != 0 && thumb.length <= 6000 && decryptedMessage.media.thumb_w <= 100 && decryptedMessage.media.thumb_h <= 100) {
          photoSize=new TLRPC.TL_photoCachedSize();
          photoSize.bytes=thumb;
          photoSize.w=decryptedMessage.media.thumb_w;
          photoSize.h=decryptedMessage.media.thumb_h;
          photoSize.type="s";
          photoSize.location=new TLRPC.TL_fileLocationUnavailable();
        }
 else {
          photoSize=new TLRPC.TL_photoSizeEmpty();
          photoSize.type="s";
        }
        newMessage.media.document.thumbs.add(photoSize);
        newMessage.media.document.flags|=1;
        TLRPC.TL_documentAttributeVideo attributeVideo=new TLRPC.TL_documentAttributeVideo();
        attributeVideo.w=decryptedMessage.media.w;
        attributeVideo.h=decryptedMessage.media.h;
        attributeVideo.duration=decryptedMessage.media.duration;
        attributeVideo.supports_streaming=false;
        newMessage.media.document.attributes.add(attributeVideo);
        if (newMessage.ttl != 0) {
          newMessage.media.ttl_seconds=newMessage.ttl;
          newMessage.media.flags|=4;
        }
        if (newMessage.ttl != 0) {
          newMessage.ttl=Math.max(decryptedMessage.media.duration + 1,newMessage.ttl);
        }
      }
 else       if (decryptedMessage.media instanceof TLRPC.TL_decryptedMessageMediaDocument) {
        if (decryptedMessage.media.key == null || decryptedMessage.media.key.length != 32 || decryptedMessage.media.iv == null || decryptedMessage.media.iv.length != 32) {
          return null;
        }
        newMessage.media=new TLRPC.TL_messageMediaDocument();
        newMessage.media.flags|=3;
        newMessage.message=decryptedMessage.media.caption != null ? decryptedMessage.media.caption : "";
        newMessage.media.document=new TLRPC.TL_documentEncrypted();
        newMessage.media.document.id=file.id;
        newMessage.media.document.access_hash=file.access_hash;
        newMessage.media.document.date=date;
        if (decryptedMessage.media instanceof TLRPC.TL_decryptedMessageMediaDocument_layer8) {
          TLRPC.TL_documentAttributeFilename fileName=new TLRPC.TL_documentAttributeFilename();
          fileName.file_name=decryptedMessage.media.file_name;
          newMessage.media.document.attributes.add(fileName);
        }
 else {
          newMessage.media.document.attributes=decryptedMessage.media.attributes;
        }
        newMessage.media.document.mime_type=decryptedMessage.media.mime_type;
        newMessage.media.document.size=decryptedMessage.media.size != 0 ? Math.min(decryptedMessage.media.size,file.size) : file.size;
        newMessage.media.document.key=decryptedMessage.media.key;
        newMessage.media.document.iv=decryptedMessage.media.iv;
        if (newMessage.media.document.mime_type == null) {
          newMessage.media.document.mime_type="";
        }
        byte[] thumb=((TLRPC.TL_decryptedMessageMediaDocument)decryptedMessage.media).thumb;
        TLRPC.PhotoSize photoSize;
        if (thumb != null && thumb.length != 0 && thumb.length <= 6000 && decryptedMessage.media.thumb_w <= 100 && decryptedMessage.media.thumb_h <= 100) {
          photoSize=new TLRPC.TL_photoCachedSize();
          photoSize.bytes=thumb;
          photoSize.w=decryptedMessage.media.thumb_w;
          photoSize.h=decryptedMessage.media.thumb_h;
          photoSize.type="s";
          photoSize.location=new TLRPC.TL_fileLocationUnavailable();
        }
 else {
          photoSize=new TLRPC.TL_photoSizeEmpty();
          photoSize.type="s";
        }
        newMessage.media.document.thumbs.add(photoSize);
        newMessage.media.document.flags|=1;
        newMessage.media.document.dc_id=file.dc_id;
        if (MessageObject.isVoiceMessage(newMessage) || MessageObject.isRoundVideoMessage(newMessage)) {
          newMessage.media_unread=true;
        }
      }
 else       if (decryptedMessage.media instanceof TLRPC.TL_decryptedMessageMediaExternalDocument) {
        newMessage.media=new TLRPC.TL_messageMediaDocument();
        newMessage.media.flags|=3;
        newMessage.message="";
        newMessage.media.document=new TLRPC.TL_document();
        newMessage.media.document.id=decryptedMessage.media.id;
        newMessage.media.document.access_hash=decryptedMessage.media.access_hash;
        newMessage.media.document.file_reference=new byte[0];
        newMessage.media.document.date=decryptedMessage.media.date;
        newMessage.media.document.attributes=decryptedMessage.media.attributes;
        newMessage.media.document.mime_type=decryptedMessage.media.mime_type;
        newMessage.media.document.dc_id=decryptedMessage.media.dc_id;
        newMessage.media.document.size=decryptedMessage.media.size;
        newMessage.media.document.thumbs.add(((TLRPC.TL_decryptedMessageMediaExternalDocument)decryptedMessage.media).thumb);
        newMessage.media.document.flags|=1;
        if (newMessage.media.document.mime_type == null) {
          newMessage.media.document.mime_type="";
        }
      }
 else       if (decryptedMessage.media instanceof TLRPC.TL_decryptedMessageMediaAudio) {
        if (decryptedMessage.media.key == null || decryptedMessage.media.key.length != 32 || decryptedMessage.media.iv == null || decryptedMessage.media.iv.length != 32) {
          return null;
        }
        newMessage.media=new TLRPC.TL_messageMediaDocument();
        newMessage.media.flags|=3;
        newMessage.media.document=new TLRPC.TL_documentEncrypted();
        newMessage.media.document.key=decryptedMessage.media.key;
        newMessage.media.document.iv=decryptedMessage.media.iv;
        newMessage.media.document.id=file.id;
        newMessage.media.document.access_hash=file.access_hash;
        newMessage.media.document.date=date;
        newMessage.media.document.size=file.size;
        newMessage.media.document.dc_id=file.dc_id;
        newMessage.media.document.mime_type=decryptedMessage.media.mime_type;
        newMessage.message=decryptedMessage.media.caption != null ? decryptedMessage.media.caption : "";
        if (newMessage.media.document.mime_type == null) {
          newMessage.media.document.mime_type="audio/ogg";
        }
        TLRPC.TL_documentAttributeAudio attributeAudio=new TLRPC.TL_documentAttributeAudio();
        attributeAudio.duration=decryptedMessage.media.duration;
        attributeAudio.voice=true;
        newMessage.media.document.attributes.add(attributeAudio);
        if (newMessage.ttl != 0) {
          newMessage.ttl=Math.max(decryptedMessage.media.duration + 1,newMessage.ttl);
        }
        if (newMessage.media.document.thumbs.isEmpty()) {
          TLRPC.PhotoSize thumb=new TLRPC.TL_photoSizeEmpty();
          thumb.type="s";
          newMessage.media.document.thumbs.add(thumb);
        }
      }
 else       if (decryptedMessage.media instanceof TLRPC.TL_decryptedMessageMediaVenue) {
        newMessage.media=new TLRPC.TL_messageMediaVenue();
        newMessage.media.geo=new TLRPC.TL_geoPoint();
        newMessage.media.geo.lat=decryptedMessage.media.lat;
        newMessage.media.geo._long=decryptedMessage.media._long;
        newMessage.media.title=decryptedMessage.media.title;
        newMessage.media.address=decryptedMessage.media.address;
        newMessage.media.provider=decryptedMessage.media.provider;
        newMessage.media.venue_id=decryptedMessage.media.venue_id;
        newMessage.media.venue_type="";
      }
 else {
        return null;
      }
      if (newMessage.ttl != 0 && newMessage.media.ttl_seconds == 0) {
        newMessage.media.ttl_seconds=newMessage.ttl;
        newMessage.media.flags|=4;
      }
      return newMessage;
    }
 else     if (object instanceof TLRPC.TL_decryptedMessageService) {
      final TLRPC.TL_decryptedMessageService serviceMessage=(TLRPC.TL_decryptedMessageService)object;
      if (serviceMessage.action instanceof TLRPC.TL_decryptedMessageActionSetMessageTTL || serviceMessage.action instanceof TLRPC.TL_decryptedMessageActionScreenshotMessages) {
        TLRPC.TL_messageService newMessage=new TLRPC.TL_messageService();
        if (serviceMessage.action instanceof TLRPC.TL_decryptedMessageActionSetMessageTTL) {
          newMessage.action=new TLRPC.TL_messageEncryptedAction();
          if (serviceMessage.action.ttl_seconds < 0 || serviceMessage.action.ttl_seconds > 60 * 60 * 24 * 365) {
            serviceMessage.action.ttl_seconds=60 * 60 * 24 * 365;
          }
          chat.ttl=serviceMessage.action.ttl_seconds;
          newMessage.action.encryptedAction=serviceMessage.action;
          MessagesStorage.getInstance(currentAccount).updateEncryptedChatTTL(chat);
        }
 else         if (serviceMessage.action instanceof TLRPC.TL_decryptedMessageActionScreenshotMessages) {
          newMessage.action=new TLRPC.TL_messageEncryptedAction();
          newMessage.action.encryptedAction=serviceMessage.action;
        }
        newMessage.local_id=newMessage.id=UserConfig.getInstance(currentAccount).getNewMessageId();
        UserConfig.getInstance(currentAccount).saveConfig(false);
        newMessage.unread=true;
        newMessage.flags=TLRPC.MESSAGE_FLAG_HAS_FROM_ID;
        newMessage.date=date;
        newMessage.from_id=from_id;
        newMessage.to_id=new TLRPC.TL_peerUser();
        newMessage.to_id.user_id=UserConfig.getInstance(currentAccount).getClientUserId();
        newMessage.dialog_id=((long)chat.id) << 32;
        return newMessage;
      }
 else       if (serviceMessage.action instanceof TLRPC.TL_decryptedMessageActionFlushHistory) {
        final long did=((long)chat.id) << 32;
        AndroidUtilities.runOnUIThread(() -> {
          TLRPC.Dialog dialog=MessagesController.getInstance(currentAccount).dialogs_dict.get(did);
          if (dialog != null) {
            dialog.unread_count=0;
            MessagesController.getInstance(currentAccount).dialogMessage.remove(dialog.id);
          }
          MessagesStorage.getInstance(currentAccount).getStorageQueue().postRunnable(() -> AndroidUtilities.runOnUIThread(() -> {
            NotificationsController.getInstance(currentAccount).processReadMessages(null,did,0,Integer.MAX_VALUE,false);
            LongSparseArray<Integer> dialogsToUpdate=new LongSparseArray<>(1);
            dialogsToUpdate.put(did,0);
            NotificationsController.getInstance(currentAccount).processDialogsUpdateRead(dialogsToUpdate);
          }
));
          MessagesStorage.getInstance(currentAccount).deleteDialog(did,1);
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.dialogsNeedReload);
          NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.removeAllMessagesFromDialog,did,false);
        }
);
        return null;
      }
 else       if (serviceMessage.action instanceof TLRPC.TL_decryptedMessageActionDeleteMessages) {
        if (!serviceMessage.action.random_ids.isEmpty()) {
          pendingEncMessagesToDelete.addAll(serviceMessage.action.random_ids);
        }
        return null;
      }
 else       if (serviceMessage.action instanceof TLRPC.TL_decryptedMessageActionReadMessages) {
        if (!serviceMessage.action.random_ids.isEmpty()) {
          int time=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
          MessagesStorage.getInstance(currentAccount).createTaskForSecretChat(chat.id,time,time,1,serviceMessage.action.random_ids);
        }
      }
 else       if (serviceMessage.action instanceof TLRPC.TL_decryptedMessageActionNotifyLayer) {
        applyPeerLayer(chat,serviceMessage.action.layer);
      }
 else       if (serviceMessage.action instanceof TLRPC.TL_decryptedMessageActionRequestKey) {
        if (chat.exchange_id != 0) {
          if (chat.exchange_id > serviceMessage.action.exchange_id) {
            if (BuildVars.LOGS_ENABLED) {
              FileLog.d("we already have request key with higher exchange_id");
            }
            return null;
          }
 else {
            sendAbortKeyMessage(chat,null,chat.exchange_id);
          }
        }
        byte[] salt=new byte[256];
        Utilities.random.nextBytes(salt);
        BigInteger p=new BigInteger(1,MessagesStorage.getInstance(currentAccount).getSecretPBytes());
        BigInteger g_b=BigInteger.valueOf(MessagesStorage.getInstance(currentAccount).getSecretG());
        g_b=g_b.modPow(new BigInteger(1,salt),p);
        BigInteger g_a=new BigInteger(1,serviceMessage.action.g_a);
        if (!Utilities.isGoodGaAndGb(g_a,p)) {
          sendAbortKeyMessage(chat,null,serviceMessage.action.exchange_id);
          return null;
        }
        byte[] g_b_bytes=g_b.toByteArray();
        if (g_b_bytes.length > 256) {
          byte[] correctedAuth=new byte[256];
          System.arraycopy(g_b_bytes,1,correctedAuth,0,256);
          g_b_bytes=correctedAuth;
        }
        g_a=g_a.modPow(new BigInteger(1,salt),p);
        byte[] authKey=g_a.toByteArray();
        if (authKey.length > 256) {
          byte[] correctedAuth=new byte[256];
          System.arraycopy(authKey,authKey.length - 256,correctedAuth,0,256);
          authKey=correctedAuth;
        }
 else         if (authKey.length < 256) {
          byte[] correctedAuth=new byte[256];
          System.arraycopy(authKey,0,correctedAuth,256 - authKey.length,authKey.length);
          for (int a=0; a < 256 - authKey.length; a++) {
            correctedAuth[a]=0;
          }
          authKey=correctedAuth;
        }
        byte[] authKeyHash=Utilities.computeSHA1(authKey);
        byte[] authKeyId=new byte[8];
        System.arraycopy(authKeyHash,authKeyHash.length - 8,authKeyId,0,8);
        chat.exchange_id=serviceMessage.action.exchange_id;
        chat.future_auth_key=authKey;
        chat.future_key_fingerprint=Utilities.bytesToLong(authKeyId);
        chat.g_a_or_b=g_b_bytes;
        MessagesStorage.getInstance(currentAccount).updateEncryptedChat(chat);
        sendAcceptKeyMessage(chat,null);
      }
 else       if (serviceMessage.action instanceof TLRPC.TL_decryptedMessageActionAcceptKey) {
        if (chat.exchange_id == serviceMessage.action.exchange_id) {
          BigInteger p=new BigInteger(1,MessagesStorage.getInstance(currentAccount).getSecretPBytes());
          BigInteger i_authKey=new BigInteger(1,serviceMessage.action.g_b);
          if (!Utilities.isGoodGaAndGb(i_authKey,p)) {
            chat.future_auth_key=new byte[256];
            chat.future_key_fingerprint=0;
            chat.exchange_id=0;
            MessagesStorage.getInstance(currentAccount).updateEncryptedChat(chat);
            sendAbortKeyMessage(chat,null,serviceMessage.action.exchange_id);
            return null;
          }
          i_authKey=i_authKey.modPow(new BigInteger(1,chat.a_or_b),p);
          byte[] authKey=i_authKey.toByteArray();
          if (authKey.length > 256) {
            byte[] correctedAuth=new byte[256];
            System.arraycopy(authKey,authKey.length - 256,correctedAuth,0,256);
            authKey=correctedAuth;
          }
 else           if (authKey.length < 256) {
            byte[] correctedAuth=new byte[256];
            System.arraycopy(authKey,0,correctedAuth,256 - authKey.length,authKey.length);
            for (int a=0; a < 256 - authKey.length; a++) {
              correctedAuth[a]=0;
            }
            authKey=correctedAuth;
          }
          byte[] authKeyHash=Utilities.computeSHA1(authKey);
          byte[] authKeyId=new byte[8];
          System.arraycopy(authKeyHash,authKeyHash.length - 8,authKeyId,0,8);
          long fingerprint=Utilities.bytesToLong(authKeyId);
          if (serviceMessage.action.key_fingerprint == fingerprint) {
            chat.future_auth_key=authKey;
            chat.future_key_fingerprint=fingerprint;
            MessagesStorage.getInstance(currentAccount).updateEncryptedChat(chat);
            sendCommitKeyMessage(chat,null);
          }
 else {
            chat.future_auth_key=new byte[256];
            chat.future_key_fingerprint=0;
            chat.exchange_id=0;
            MessagesStorage.getInstance(currentAccount).updateEncryptedChat(chat);
            sendAbortKeyMessage(chat,null,serviceMessage.action.exchange_id);
          }
        }
 else {
          chat.future_auth_key=new byte[256];
          chat.future_key_fingerprint=0;
          chat.exchange_id=0;
          MessagesStorage.getInstance(currentAccount).updateEncryptedChat(chat);
          sendAbortKeyMessage(chat,null,serviceMessage.action.exchange_id);
        }
      }
 else       if (serviceMessage.action instanceof TLRPC.TL_decryptedMessageActionCommitKey) {
        if (chat.exchange_id == serviceMessage.action.exchange_id && chat.future_key_fingerprint == serviceMessage.action.key_fingerprint) {
          long old_fingerpring=chat.key_fingerprint;
          byte[] old_key=chat.auth_key;
          chat.key_fingerprint=chat.future_key_fingerprint;
          chat.auth_key=chat.future_auth_key;
          chat.key_create_date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
          chat.future_auth_key=old_key;
          chat.future_key_fingerprint=old_fingerpring;
          chat.key_use_count_in=0;
          chat.key_use_count_out=0;
          chat.exchange_id=0;
          MessagesStorage.getInstance(currentAccount).updateEncryptedChat(chat);
          sendNoopMessage(chat,null);
        }
 else {
          chat.future_auth_key=new byte[256];
          chat.future_key_fingerprint=0;
          chat.exchange_id=0;
          MessagesStorage.getInstance(currentAccount).updateEncryptedChat(chat);
          sendAbortKeyMessage(chat,null,serviceMessage.action.exchange_id);
        }
      }
 else       if (serviceMessage.action instanceof TLRPC.TL_decryptedMessageActionAbortKey) {
        if (chat.exchange_id == serviceMessage.action.exchange_id) {
          chat.future_auth_key=new byte[256];
          chat.future_key_fingerprint=0;
          chat.exchange_id=0;
          MessagesStorage.getInstance(currentAccount).updateEncryptedChat(chat);
        }
      }
 else       if (serviceMessage.action instanceof TLRPC.TL_decryptedMessageActionNoop) {
      }
 else       if (serviceMessage.action instanceof TLRPC.TL_decryptedMessageActionResend) {
        if (serviceMessage.action.end_seq_no < chat.in_seq_no || serviceMessage.action.end_seq_no < serviceMessage.action.start_seq_no) {
          return null;
        }
        if (serviceMessage.action.start_seq_no < chat.in_seq_no) {
          serviceMessage.action.start_seq_no=chat.in_seq_no;
        }
        resendMessages(serviceMessage.action.start_seq_no,serviceMessage.action.end_seq_no,chat);
      }
 else {
        return null;
      }
    }
 else {
      if (BuildVars.LOGS_ENABLED) {
        FileLog.e("unknown message " + object);
      }
    }
  }
 else {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.e("unknown TLObject");
    }
  }
  return null;
}
