public static ArrayList<TLRPC.User> loadVCardFromStream(Uri uri,int currentAccount,boolean asset,ArrayList<VcardItem> items,String name){
  ArrayList<TLRPC.User> result=null;
  try {
    InputStream stream;
    if (asset) {
      AssetFileDescriptor fd=ApplicationLoader.applicationContext.getContentResolver().openAssetFileDescriptor(uri,"r");
      stream=fd.createInputStream();
    }
 else {
      ContentResolver cr=ApplicationLoader.applicationContext.getContentResolver();
      stream=cr.openInputStream(uri);
    }
    ArrayList<VcardData> vcardDatas=new ArrayList<>();
    VcardData currentData=null;
    BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(stream,"UTF-8"));
    String line;
    String originalLine;
    String pendingLine=null;
    boolean currentIsPhoto=false;
    VcardItem currentItem=null;
    while ((originalLine=line=bufferedReader.readLine()) != null) {
      if (originalLine.startsWith("PHOTO")) {
        currentIsPhoto=true;
        continue;
      }
 else {
        if (originalLine.indexOf(':') >= 0) {
          currentItem=null;
          currentIsPhoto=false;
          if (originalLine.startsWith("BEGIN:VCARD")) {
            vcardDatas.add(currentData=new VcardData());
            currentData.name=name;
          }
 else           if (originalLine.startsWith("END:VCARD")) {
          }
 else           if (items != null) {
            if (originalLine.startsWith("TEL")) {
              currentItem=new VcardItem();
              currentItem.type=0;
            }
 else             if (originalLine.startsWith("EMAIL")) {
              currentItem=new VcardItem();
              currentItem.type=1;
            }
 else             if (originalLine.startsWith("ADR") || originalLine.startsWith("LABEL") || originalLine.startsWith("GEO")) {
              currentItem=new VcardItem();
              currentItem.type=2;
            }
 else             if (originalLine.startsWith("URL")) {
              currentItem=new VcardItem();
              currentItem.type=3;
            }
 else             if (originalLine.startsWith("NOTE")) {
              currentItem=new VcardItem();
              currentItem.type=4;
            }
 else             if (originalLine.startsWith("BDAY")) {
              currentItem=new VcardItem();
              currentItem.type=5;
            }
 else             if (originalLine.startsWith("ORG") || originalLine.startsWith("TITLE") || originalLine.startsWith("ROLE")) {
              if (currentItem == null) {
                currentItem=new VcardItem();
                currentItem.type=6;
              }
            }
 else             if (originalLine.startsWith("X-ANDROID")) {
              currentItem=new VcardItem();
              currentItem.type=-1;
            }
 else             if (originalLine.startsWith("X-PHONETIC")) {
              currentItem=null;
            }
 else             if (originalLine.startsWith("X-")) {
              currentItem=new VcardItem();
              currentItem.type=20;
            }
            if (currentItem != null && currentItem.type >= 0) {
              items.add(currentItem);
            }
          }
        }
      }
      if (!currentIsPhoto && currentData != null) {
        if (currentItem == null) {
          if (currentData.vcard.length() > 0) {
            currentData.vcard.append('\n');
          }
          currentData.vcard.append(originalLine);
        }
 else {
          currentItem.vcardData.add(originalLine);
        }
      }
      if (pendingLine != null) {
        pendingLine+=line;
        line=pendingLine;
        pendingLine=null;
      }
      if (line.contains("=QUOTED-PRINTABLE") && line.endsWith("=")) {
        pendingLine=line.substring(0,line.length() - 1);
        continue;
      }
      if (!currentIsPhoto && currentData != null && currentItem != null) {
        currentItem.fullData=line;
      }
      int idx=line.indexOf(":");
      String[] args;
      if (idx >= 0) {
        args=new String[]{line.substring(0,idx),line.substring(idx + 1).trim()};
      }
 else {
        args=new String[]{line.trim()};
      }
      if (args.length < 2 || currentData == null) {
        continue;
      }
      if (args[0].startsWith("FN") || args[0].startsWith("ORG") && TextUtils.isEmpty(currentData.name)) {
        String nameEncoding=null;
        String nameCharset=null;
        String[] params=args[0].split(";");
        for (        String param : params) {
          String[] args2=param.split("=");
          if (args2.length != 2) {
            continue;
          }
          if (args2[0].equals("CHARSET")) {
            nameCharset=args2[1];
          }
 else           if (args2[0].equals("ENCODING")) {
            nameEncoding=args2[1];
          }
        }
        currentData.name=args[1];
        if (nameEncoding != null && nameEncoding.equalsIgnoreCase("QUOTED-PRINTABLE")) {
          byte[] bytes=decodeQuotedPrintable(getStringBytes(currentData.name));
          if (bytes != null && bytes.length != 0) {
            String decodedName=new String(bytes,nameCharset);
            if (decodedName != null) {
              currentData.name=decodedName;
            }
          }
        }
      }
 else       if (args[0].startsWith("TEL")) {
        currentData.phones.add(args[1]);
      }
    }
    try {
      bufferedReader.close();
      stream.close();
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    for (int a=0; a < vcardDatas.size(); a++) {
      VcardData vcardData=vcardDatas.get(a);
      if (vcardData.name != null && !vcardData.phones.isEmpty()) {
        if (result == null) {
          result=new ArrayList<>();
        }
        String phoneToUse=vcardData.phones.get(0);
        for (int b=0; b < vcardData.phones.size(); b++) {
          String phone=vcardData.phones.get(b);
          String sphone=phone.substring(Math.max(0,phone.length() - 7));
          if (ContactsController.getInstance(currentAccount).contactsByShortPhone.get(sphone) != null) {
            phoneToUse=phone;
            break;
          }
        }
        TLRPC.User user=new TLRPC.TL_userContact_old2();
        user.phone=phoneToUse;
        user.first_name=vcardData.name;
        user.last_name="";
        user.id=0;
        user.restriction_reason=vcardData.vcard.toString();
        result.add(user);
      }
    }
  }
 catch (  Throwable e) {
    FileLog.e(e);
  }
  return result;
}
