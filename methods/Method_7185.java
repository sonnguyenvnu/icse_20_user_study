@UiThread public static void prepareSendingBotContextResult(final TLRPC.BotInlineResult result,final HashMap<String,String> params,final long dialog_id,final MessageObject reply_to_msg){
  if (result == null) {
    return;
  }
  final int currentAccount=UserConfig.selectedAccount;
  if (result.send_message instanceof TLRPC.TL_botInlineMessageMediaAuto) {
    new Thread(() -> {
      boolean isEncrypted=(int)dialog_id == 0;
      String finalPath=null;
      TLRPC.TL_document document=null;
      TLRPC.TL_photo photo=null;
      TLRPC.TL_game game=null;
      if ("game".equals(result.type)) {
        if ((int)dialog_id == 0) {
          return;
        }
        game=new TLRPC.TL_game();
        game.title=result.title;
        game.description=result.description;
        game.short_name=result.id;
        game.photo=result.photo;
        if (game.photo == null) {
          game.photo=new TLRPC.TL_photoEmpty();
        }
        if (result.document instanceof TLRPC.TL_document) {
          game.document=result.document;
          game.flags|=1;
        }
      }
 else       if (result instanceof TLRPC.TL_botInlineMediaResult) {
        if (result.document != null) {
          if (result.document instanceof TLRPC.TL_document) {
            document=(TLRPC.TL_document)result.document;
          }
        }
 else         if (result.photo != null) {
          if (result.photo instanceof TLRPC.TL_photo) {
            photo=(TLRPC.TL_photo)result.photo;
          }
        }
      }
 else       if (result.content != null) {
        File f=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),Utilities.MD5(result.content.url) + "." + ImageLoader.getHttpUrlExtension(result.content.url,"file"));
        if (f.exists()) {
          finalPath=f.getAbsolutePath();
        }
 else {
          finalPath=result.content.url;
        }
switch (result.type) {
case "audio":
case "voice":
case "file":
case "video":
case "sticker":
case "gif":
{
            document=new TLRPC.TL_document();
            document.id=0;
            document.size=0;
            document.dc_id=0;
            document.mime_type=result.content.mime_type;
            document.file_reference=new byte[0];
            document.date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
            TLRPC.TL_documentAttributeFilename fileName=new TLRPC.TL_documentAttributeFilename();
            document.attributes.add(fileName);
switch (result.type) {
case "gif":
{
                fileName.file_name="animation.gif";
                if (finalPath.endsWith("mp4")) {
                  document.mime_type="video/mp4";
                  document.attributes.add(new TLRPC.TL_documentAttributeAnimated());
                }
 else {
                  document.mime_type="image/gif";
                }
                try {
                  int side=isEncrypted ? 90 : 320;
                  Bitmap bitmap;
                  if (finalPath.endsWith("mp4")) {
                    bitmap=ThumbnailUtils.createVideoThumbnail(finalPath,MediaStore.Video.Thumbnails.MINI_KIND);
                  }
 else {
                    bitmap=ImageLoader.loadBitmap(finalPath,null,side,side,true);
                  }
                  if (bitmap != null) {
                    TLRPC.PhotoSize thumb=ImageLoader.scaleAndSaveImage(bitmap,side,side,side > 90 ? 80 : 55,false);
                    if (thumb != null) {
                      document.thumbs.add(thumb);
                      document.flags|=1;
                    }
                    bitmap.recycle();
                  }
                }
 catch (                Throwable e) {
                  FileLog.e(e);
                }
                break;
              }
case "voice":
{
              TLRPC.TL_documentAttributeAudio audio=new TLRPC.TL_documentAttributeAudio();
              audio.duration=MessageObject.getInlineResultDuration(result);
              audio.voice=true;
              fileName.file_name="audio.ogg";
              document.attributes.add(audio);
              break;
            }
case "audio":
{
            TLRPC.TL_documentAttributeAudio audio=new TLRPC.TL_documentAttributeAudio();
            audio.duration=MessageObject.getInlineResultDuration(result);
            audio.title=result.title;
            audio.flags|=1;
            if (result.description != null) {
              audio.performer=result.description;
              audio.flags|=2;
            }
            fileName.file_name="audio.mp3";
            document.attributes.add(audio);
            break;
          }
case "file":
{
          int idx=result.content.mime_type.lastIndexOf('/');
          if (idx != -1) {
            fileName.file_name="file." + result.content.mime_type.substring(idx + 1);
          }
 else {
            fileName.file_name="file";
          }
          break;
        }
case "video":
{
        fileName.file_name="video.mp4";
        TLRPC.TL_documentAttributeVideo attributeVideo=new TLRPC.TL_documentAttributeVideo();
        int wh[]=MessageObject.getInlineResultWidthAndHeight(result);
        attributeVideo.w=wh[0];
        attributeVideo.h=wh[1];
        attributeVideo.duration=MessageObject.getInlineResultDuration(result);
        attributeVideo.supports_streaming=true;
        document.attributes.add(attributeVideo);
        try {
          if (result.thumb != null) {
            String thumbPath=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),Utilities.MD5(result.thumb.url) + "." + ImageLoader.getHttpUrlExtension(result.thumb.url,"jpg")).getAbsolutePath();
            Bitmap bitmap=ImageLoader.loadBitmap(thumbPath,null,90,90,true);
            if (bitmap != null) {
              TLRPC.PhotoSize thumb=ImageLoader.scaleAndSaveImage(bitmap,90,90,55,false);
              if (thumb != null) {
                document.thumbs.add(thumb);
                document.flags|=1;
              }
              bitmap.recycle();
            }
          }
        }
 catch (        Throwable e) {
          FileLog.e(e);
        }
        break;
      }
case "sticker":
{
      TLRPC.TL_documentAttributeSticker attributeSticker=new TLRPC.TL_documentAttributeSticker();
      attributeSticker.alt="";
      attributeSticker.stickerset=new TLRPC.TL_inputStickerSetEmpty();
      document.attributes.add(attributeSticker);
      TLRPC.TL_documentAttributeImageSize attributeImageSize=new TLRPC.TL_documentAttributeImageSize();
      int wh[]=MessageObject.getInlineResultWidthAndHeight(result);
      attributeImageSize.w=wh[0];
      attributeImageSize.h=wh[1];
      document.attributes.add(attributeImageSize);
      fileName.file_name="sticker.webp";
      try {
        if (result.thumb != null) {
          String thumbPath=new File(FileLoader.getDirectory(FileLoader.MEDIA_DIR_CACHE),Utilities.MD5(result.thumb.url) + "." + ImageLoader.getHttpUrlExtension(result.thumb.url,"webp")).getAbsolutePath();
          Bitmap bitmap=ImageLoader.loadBitmap(thumbPath,null,90,90,true);
          if (bitmap != null) {
            TLRPC.PhotoSize thumb=ImageLoader.scaleAndSaveImage(bitmap,90,90,55,false);
            if (thumb != null) {
              document.thumbs.add(thumb);
              document.flags|=1;
            }
            bitmap.recycle();
          }
        }
      }
 catch (      Throwable e) {
        FileLog.e(e);
      }
      break;
    }
}
if (fileName.file_name == null) {
  fileName.file_name="file";
}
if (document.mime_type == null) {
  document.mime_type="application/octet-stream";
}
if (document.thumbs.isEmpty()) {
  TLRPC.PhotoSize thumb=new TLRPC.TL_photoSize();
  int wh[]=MessageObject.getInlineResultWidthAndHeight(result);
  thumb.w=wh[0];
  thumb.h=wh[1];
  thumb.size=0;
  thumb.location=new TLRPC.TL_fileLocationUnavailable();
  thumb.type="x";
  document.thumbs.add(thumb);
  document.flags|=1;
}
break;
}
case "photo":
{
if (f.exists()) {
photo=SendMessagesHelper.getInstance(currentAccount).generatePhotoSizes(finalPath,null);
}
if (photo == null) {
photo=new TLRPC.TL_photo();
photo.date=ConnectionsManager.getInstance(currentAccount).getCurrentTime();
photo.file_reference=new byte[0];
TLRPC.TL_photoSize photoSize=new TLRPC.TL_photoSize();
int wh[]=MessageObject.getInlineResultWidthAndHeight(result);
photoSize.w=wh[0];
photoSize.h=wh[1];
photoSize.size=1;
photoSize.location=new TLRPC.TL_fileLocationUnavailable();
photoSize.type="x";
photo.sizes.add(photoSize);
}
break;
}
}
}
final String finalPathFinal=finalPath;
final TLRPC.TL_document finalDocument=document;
final TLRPC.TL_photo finalPhoto=photo;
final TLRPC.TL_game finalGame=game;
if (params != null && result.content != null) {
params.put("originalPath",result.content.url);
}
AndroidUtilities.runOnUIThread(() -> {
if (finalDocument != null) {
SendMessagesHelper.getInstance(currentAccount).sendMessage(finalDocument,null,finalPathFinal,dialog_id,reply_to_msg,result.send_message.message,result.send_message.entities,result.send_message.reply_markup,params,0,result);
}
 else if (finalPhoto != null) {
SendMessagesHelper.getInstance(currentAccount).sendMessage(finalPhoto,result.content != null ? result.content.url : null,dialog_id,reply_to_msg,result.send_message.message,result.send_message.entities,result.send_message.reply_markup,params,0,result);
}
 else if (finalGame != null) {
SendMessagesHelper.getInstance(currentAccount).sendMessage(finalGame,dialog_id,result.send_message.reply_markup,params);
}
}
);
}
).run();
}
 else if (result.send_message instanceof TLRPC.TL_botInlineMessageText) {
TLRPC.WebPage webPage=null;
if ((int)dialog_id == 0) {
for (int a=0; a < result.send_message.entities.size(); a++) {
TLRPC.MessageEntity entity=result.send_message.entities.get(a);
if (entity instanceof TLRPC.TL_messageEntityUrl) {
webPage=new TLRPC.TL_webPagePending();
webPage.url=result.send_message.message.substring(entity.offset,entity.offset + entity.length);
break;
}
}
}
SendMessagesHelper.getInstance(currentAccount).sendMessage(result.send_message.message,dialog_id,reply_to_msg,webPage,!result.send_message.no_webpage,result.send_message.entities,result.send_message.reply_markup,params);
}
 else if (result.send_message instanceof TLRPC.TL_botInlineMessageMediaVenue) {
TLRPC.TL_messageMediaVenue venue=new TLRPC.TL_messageMediaVenue();
venue.geo=result.send_message.geo;
venue.address=result.send_message.address;
venue.title=result.send_message.title;
venue.provider=result.send_message.provider;
venue.venue_id=result.send_message.venue_id;
venue.venue_type=venue.venue_id=result.send_message.venue_type;
if (venue.venue_type == null) {
venue.venue_type="";
}
SendMessagesHelper.getInstance(currentAccount).sendMessage(venue,dialog_id,reply_to_msg,result.send_message.reply_markup,params);
}
 else if (result.send_message instanceof TLRPC.TL_botInlineMessageMediaGeo) {
if (result.send_message.period != 0) {
TLRPC.TL_messageMediaGeoLive location=new TLRPC.TL_messageMediaGeoLive();
location.period=result.send_message.period;
location.geo=result.send_message.geo;
SendMessagesHelper.getInstance(currentAccount).sendMessage(location,dialog_id,reply_to_msg,result.send_message.reply_markup,params);
}
 else {
TLRPC.TL_messageMediaGeo location=new TLRPC.TL_messageMediaGeo();
location.geo=result.send_message.geo;
SendMessagesHelper.getInstance(currentAccount).sendMessage(location,dialog_id,reply_to_msg,result.send_message.reply_markup,params);
}
}
 else if (result.send_message instanceof TLRPC.TL_botInlineMessageMediaContact) {
TLRPC.User user=new TLRPC.TL_user();
user.phone=result.send_message.phone_number;
user.first_name=result.send_message.first_name;
user.last_name=result.send_message.last_name;
user.restriction_reason=result.send_message.vcard;
SendMessagesHelper.getInstance(currentAccount).sendMessage(user,dialog_id,reply_to_msg,result.send_message.reply_markup,params);
}
}
