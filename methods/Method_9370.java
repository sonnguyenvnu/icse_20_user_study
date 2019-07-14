private void createRequestInterface(Context context){
  TLRPC.User botUser=null;
  if (currentForm != null) {
    for (int a=0; a < currentForm.users.size(); a++) {
      TLRPC.User user=currentForm.users.get(a);
      if (user.id == currentBotId) {
        botUser=user;
        break;
      }
    }
  }
  FrameLayout frameLayout=(FrameLayout)fragmentView;
  actionBar.setTitle(LocaleController.getString("TelegramPassport",R.string.TelegramPassport));
  actionBar.createMenu().addItem(info_item,R.drawable.profile_info);
  if (botUser != null) {
    FrameLayout avatarContainer=new FrameLayout(context);
    linearLayout2.addView(avatarContainer,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,100));
    BackupImageView avatarImageView=new BackupImageView(context);
    avatarImageView.setRoundRadius(AndroidUtilities.dp(32));
    avatarContainer.addView(avatarImageView,LayoutHelper.createFrame(64,64,Gravity.CENTER,0,8,0,0));
    AvatarDrawable avatarDrawable=new AvatarDrawable(botUser);
    avatarImageView.setImage(ImageLocation.getForUser(botUser,false),"50_50",avatarDrawable,botUser);
    bottomCell=new TextInfoPrivacyCell(context);
    bottomCell.setBackgroundDrawable(Theme.getThemedDrawable(context,R.drawable.greydivider_top,Theme.key_windowBackgroundGrayShadow));
    bottomCell.setText(AndroidUtilities.replaceTags(LocaleController.formatString("PassportRequest",R.string.PassportRequest,UserObject.getFirstName(botUser))));
    bottomCell.getTextView().setGravity(Gravity.CENTER_HORIZONTAL);
    ((FrameLayout.LayoutParams)bottomCell.getTextView().getLayoutParams()).gravity=Gravity.CENTER_HORIZONTAL;
    linearLayout2.addView(bottomCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
  }
  headerCell=new HeaderCell(context);
  headerCell.setText(LocaleController.getString("PassportRequestedInformation",R.string.PassportRequestedInformation));
  headerCell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
  linearLayout2.addView(headerCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
  if (currentForm != null) {
    int size=currentForm.required_types.size();
    ArrayList<TLRPC.TL_secureRequiredType> personalDocuments=new ArrayList<>();
    ArrayList<TLRPC.TL_secureRequiredType> addressDocuments=new ArrayList<>();
    int personalCount=0;
    int addressCount=0;
    boolean hasPersonalInfo=false;
    boolean hasAddressInfo=false;
    for (int a=0; a < size; a++) {
      TLRPC.SecureRequiredType secureRequiredType=currentForm.required_types.get(a);
      if (secureRequiredType instanceof TLRPC.TL_secureRequiredType) {
        TLRPC.TL_secureRequiredType requiredType=(TLRPC.TL_secureRequiredType)secureRequiredType;
        if (isPersonalDocument(requiredType.type)) {
          personalDocuments.add(requiredType);
          personalCount++;
        }
 else         if (isAddressDocument(requiredType.type)) {
          addressDocuments.add(requiredType);
          addressCount++;
        }
 else         if (requiredType.type instanceof TLRPC.TL_secureValueTypePersonalDetails) {
          hasPersonalInfo=true;
        }
 else         if (requiredType.type instanceof TLRPC.TL_secureValueTypeAddress) {
          hasAddressInfo=true;
        }
      }
 else       if (secureRequiredType instanceof TLRPC.TL_secureRequiredTypeOneOf) {
        TLRPC.TL_secureRequiredTypeOneOf requiredTypeOneOf=(TLRPC.TL_secureRequiredTypeOneOf)secureRequiredType;
        if (requiredTypeOneOf.types.isEmpty()) {
          continue;
        }
        TLRPC.SecureRequiredType innerType=requiredTypeOneOf.types.get(0);
        if (!(innerType instanceof TLRPC.TL_secureRequiredType)) {
          continue;
        }
        TLRPC.TL_secureRequiredType requiredType=(TLRPC.TL_secureRequiredType)innerType;
        if (isPersonalDocument(requiredType.type)) {
          for (int b=0, size2=requiredTypeOneOf.types.size(); b < size2; b++) {
            innerType=requiredTypeOneOf.types.get(b);
            if (!(innerType instanceof TLRPC.TL_secureRequiredType)) {
              continue;
            }
            personalDocuments.add((TLRPC.TL_secureRequiredType)innerType);
          }
          personalCount++;
        }
 else         if (isAddressDocument(requiredType.type)) {
          for (int b=0, size2=requiredTypeOneOf.types.size(); b < size2; b++) {
            innerType=requiredTypeOneOf.types.get(b);
            if (!(innerType instanceof TLRPC.TL_secureRequiredType)) {
              continue;
            }
            addressDocuments.add((TLRPC.TL_secureRequiredType)innerType);
          }
          addressCount++;
        }
      }
    }
    boolean separatePersonal=!hasPersonalInfo || personalCount > 1;
    boolean separateAddress=!hasAddressInfo || addressCount > 1;
    for (int a=0; a < size; a++) {
      TLRPC.SecureRequiredType secureRequiredType=currentForm.required_types.get(a);
      ArrayList<TLRPC.TL_secureRequiredType> documentTypes;
      TLRPC.TL_secureRequiredType requiredType;
      boolean documentOnly;
      if (secureRequiredType instanceof TLRPC.TL_secureRequiredType) {
        requiredType=(TLRPC.TL_secureRequiredType)secureRequiredType;
        if (requiredType.type instanceof TLRPC.TL_secureValueTypePhone || requiredType.type instanceof TLRPC.TL_secureValueTypeEmail) {
          documentTypes=null;
          documentOnly=false;
        }
 else         if (requiredType.type instanceof TLRPC.TL_secureValueTypePersonalDetails) {
          if (separatePersonal) {
            documentTypes=null;
          }
 else {
            documentTypes=personalDocuments;
          }
          documentOnly=false;
        }
 else         if (requiredType.type instanceof TLRPC.TL_secureValueTypeAddress) {
          if (separateAddress) {
            documentTypes=null;
          }
 else {
            documentTypes=addressDocuments;
          }
          documentOnly=false;
        }
 else         if (separatePersonal && isPersonalDocument(requiredType.type)) {
          documentTypes=new ArrayList<>();
          documentTypes.add(requiredType);
          requiredType=new TLRPC.TL_secureRequiredType();
          requiredType.type=new TLRPC.TL_secureValueTypePersonalDetails();
          documentOnly=true;
        }
 else         if (separateAddress && isAddressDocument(requiredType.type)) {
          documentTypes=new ArrayList<>();
          documentTypes.add(requiredType);
          requiredType=new TLRPC.TL_secureRequiredType();
          requiredType.type=new TLRPC.TL_secureValueTypeAddress();
          documentOnly=true;
        }
 else {
          continue;
        }
      }
 else       if (secureRequiredType instanceof TLRPC.TL_secureRequiredTypeOneOf) {
        TLRPC.TL_secureRequiredTypeOneOf requiredTypeOneOf=(TLRPC.TL_secureRequiredTypeOneOf)secureRequiredType;
        if (requiredTypeOneOf.types.isEmpty()) {
          continue;
        }
        TLRPC.SecureRequiredType innerType=requiredTypeOneOf.types.get(0);
        if (!(innerType instanceof TLRPC.TL_secureRequiredType)) {
          continue;
        }
        requiredType=(TLRPC.TL_secureRequiredType)innerType;
        if (separatePersonal && isPersonalDocument(requiredType.type) || separateAddress && isAddressDocument(requiredType.type)) {
          documentTypes=new ArrayList<>();
          for (int b=0, size2=requiredTypeOneOf.types.size(); b < size2; b++) {
            innerType=requiredTypeOneOf.types.get(b);
            if (!(innerType instanceof TLRPC.TL_secureRequiredType)) {
              continue;
            }
            documentTypes.add((TLRPC.TL_secureRequiredType)innerType);
          }
          if (isPersonalDocument(requiredType.type)) {
            requiredType=new TLRPC.TL_secureRequiredType();
            requiredType.type=new TLRPC.TL_secureValueTypePersonalDetails();
          }
 else {
            requiredType=new TLRPC.TL_secureRequiredType();
            requiredType.type=new TLRPC.TL_secureValueTypeAddress();
          }
          documentOnly=true;
        }
 else {
          continue;
        }
      }
 else {
        continue;
      }
      addField(context,requiredType,documentTypes,documentOnly,a == size - 1);
    }
  }
  if (botUser != null) {
    bottomCell=new TextInfoPrivacyCell(context);
    bottomCell.setBackgroundDrawable(Theme.getThemedDrawable(context,R.drawable.greydivider_bottom,Theme.key_windowBackgroundGrayShadow));
    bottomCell.setLinkTextColorKey(Theme.key_windowBackgroundWhiteGrayText4);
    if (!TextUtils.isEmpty(currentForm.privacy_policy_url)) {
      String str2=LocaleController.formatString("PassportPolicy",R.string.PassportPolicy,UserObject.getFirstName(botUser),botUser.username);
      SpannableStringBuilder text=new SpannableStringBuilder(str2);
      int index1=str2.indexOf('*');
      int index2=str2.lastIndexOf('*');
      if (index1 != -1 && index2 != -1) {
        bottomCell.getTextView().setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        text.replace(index2,index2 + 1,"");
        text.replace(index1,index1 + 1,"");
        text.setSpan(new LinkSpan(),index1,index2 - 1,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
      bottomCell.setText(text);
    }
 else {
      bottomCell.setText(AndroidUtilities.replaceTags(LocaleController.formatString("PassportNoPolicy",R.string.PassportNoPolicy,UserObject.getFirstName(botUser),botUser.username)));
    }
    bottomCell.getTextView().setHighlightColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText4));
    bottomCell.getTextView().setGravity(Gravity.CENTER_HORIZONTAL);
    linearLayout2.addView(bottomCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
  }
  bottomLayout=new FrameLayout(context);
  bottomLayout.setBackgroundDrawable(Theme.createSelectorWithBackgroundDrawable(Theme.getColor(Theme.key_passport_authorizeBackground),Theme.getColor(Theme.key_passport_authorizeBackgroundSelected)));
  frameLayout.addView(bottomLayout,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,48,Gravity.BOTTOM));
  bottomLayout.setOnClickListener(view -> {
class ValueToSend {
      public ValueToSend(      TLRPC.TL_secureValue v,      boolean s,      boolean t){
        value=v;
        selfie_required=s;
        translation_required=t;
      }
    }
    ArrayList<ValueToSend> valuesToSend=new ArrayList<>();
    for (int a=0, size=currentForm.required_types.size(); a < size; a++) {
      TLRPC.TL_secureRequiredType requiredType;
      TLRPC.SecureRequiredType secureRequiredType=currentForm.required_types.get(a);
      if (secureRequiredType instanceof TLRPC.TL_secureRequiredType) {
        requiredType=(TLRPC.TL_secureRequiredType)secureRequiredType;
      }
 else       if (secureRequiredType instanceof TLRPC.TL_secureRequiredTypeOneOf) {
        TLRPC.TL_secureRequiredTypeOneOf requiredTypeOneOf=(TLRPC.TL_secureRequiredTypeOneOf)secureRequiredType;
        if (requiredTypeOneOf.types.isEmpty()) {
          continue;
        }
        secureRequiredType=requiredTypeOneOf.types.get(0);
        if (!(secureRequiredType instanceof TLRPC.TL_secureRequiredType)) {
          continue;
        }
        requiredType=(TLRPC.TL_secureRequiredType)secureRequiredType;
        for (int b=0, size2=requiredTypeOneOf.types.size(); b < size2; b++) {
          secureRequiredType=requiredTypeOneOf.types.get(b);
          if (!(secureRequiredType instanceof TLRPC.TL_secureRequiredType)) {
            continue;
          }
          TLRPC.TL_secureRequiredType innerType=(TLRPC.TL_secureRequiredType)secureRequiredType;
          if (getValueByType(innerType,true) != null) {
            requiredType=innerType;
            break;
          }
        }
      }
 else {
        continue;
      }
      TLRPC.TL_secureValue value=getValueByType(requiredType,true);
      if (value == null) {
        Vibrator v=(Vibrator)getParentActivity().getSystemService(Context.VIBRATOR_SERVICE);
        if (v != null) {
          v.vibrate(200);
        }
        AndroidUtilities.shakeView(getViewByType(requiredType),2,0);
        return;
      }
      String key=getNameForType(requiredType.type);
      HashMap<String,String> errors=errorsMap.get(key);
      if (errors != null && !errors.isEmpty()) {
        Vibrator v=(Vibrator)getParentActivity().getSystemService(Context.VIBRATOR_SERVICE);
        if (v != null) {
          v.vibrate(200);
        }
        AndroidUtilities.shakeView(getViewByType(requiredType),2,0);
        return;
      }
      valuesToSend.add(new ValueToSend(value,requiredType.selfie_required,requiredType.translation_required));
    }
    showEditDoneProgress(false,true);
    TLRPC.TL_account_acceptAuthorization req=new TLRPC.TL_account_acceptAuthorization();
    req.bot_id=currentBotId;
    req.scope=currentScope;
    req.public_key=currentPublicKey;
    JSONObject jsonObject=new JSONObject();
    for (int a=0, size=valuesToSend.size(); a < size; a++) {
      ValueToSend valueToSend=valuesToSend.get(a);
      TLRPC.TL_secureValue secureValue=valueToSend.value;
      JSONObject data=new JSONObject();
      if (secureValue.plain_data != null) {
        if (secureValue.plain_data instanceof TLRPC.TL_securePlainEmail) {
          TLRPC.TL_securePlainEmail securePlainEmail=(TLRPC.TL_securePlainEmail)secureValue.plain_data;
        }
 else         if (secureValue.plain_data instanceof TLRPC.TL_securePlainPhone) {
          TLRPC.TL_securePlainPhone securePlainPhone=(TLRPC.TL_securePlainPhone)secureValue.plain_data;
        }
      }
 else {
        try {
          JSONObject result=new JSONObject();
          if (secureValue.data != null) {
            byte[] decryptedSecret=decryptValueSecret(secureValue.data.secret,secureValue.data.data_hash);
            data.put("data_hash",Base64.encodeToString(secureValue.data.data_hash,Base64.NO_WRAP));
            data.put("secret",Base64.encodeToString(decryptedSecret,Base64.NO_WRAP));
            result.put("data",data);
          }
          if (!secureValue.files.isEmpty()) {
            JSONArray files=new JSONArray();
            for (int b=0, size2=secureValue.files.size(); b < size2; b++) {
              TLRPC.TL_secureFile secureFile=(TLRPC.TL_secureFile)secureValue.files.get(b);
              byte[] decryptedSecret=decryptValueSecret(secureFile.secret,secureFile.file_hash);
              JSONObject file=new JSONObject();
              file.put("file_hash",Base64.encodeToString(secureFile.file_hash,Base64.NO_WRAP));
              file.put("secret",Base64.encodeToString(decryptedSecret,Base64.NO_WRAP));
              files.put(file);
            }
            result.put("files",files);
          }
          if (secureValue.front_side instanceof TLRPC.TL_secureFile) {
            TLRPC.TL_secureFile secureFile=(TLRPC.TL_secureFile)secureValue.front_side;
            byte[] decryptedSecret=decryptValueSecret(secureFile.secret,secureFile.file_hash);
            JSONObject front=new JSONObject();
            front.put("file_hash",Base64.encodeToString(secureFile.file_hash,Base64.NO_WRAP));
            front.put("secret",Base64.encodeToString(decryptedSecret,Base64.NO_WRAP));
            result.put("front_side",front);
          }
          if (secureValue.reverse_side instanceof TLRPC.TL_secureFile) {
            TLRPC.TL_secureFile secureFile=(TLRPC.TL_secureFile)secureValue.reverse_side;
            byte[] decryptedSecret=decryptValueSecret(secureFile.secret,secureFile.file_hash);
            JSONObject reverse=new JSONObject();
            reverse.put("file_hash",Base64.encodeToString(secureFile.file_hash,Base64.NO_WRAP));
            reverse.put("secret",Base64.encodeToString(decryptedSecret,Base64.NO_WRAP));
            result.put("reverse_side",reverse);
          }
          if (valueToSend.selfie_required && secureValue.selfie instanceof TLRPC.TL_secureFile) {
            TLRPC.TL_secureFile secureFile=(TLRPC.TL_secureFile)secureValue.selfie;
            byte[] decryptedSecret=decryptValueSecret(secureFile.secret,secureFile.file_hash);
            JSONObject selfie=new JSONObject();
            selfie.put("file_hash",Base64.encodeToString(secureFile.file_hash,Base64.NO_WRAP));
            selfie.put("secret",Base64.encodeToString(decryptedSecret,Base64.NO_WRAP));
            result.put("selfie",selfie);
          }
          if (valueToSend.translation_required && !secureValue.translation.isEmpty()) {
            JSONArray translation=new JSONArray();
            for (int b=0, size2=secureValue.translation.size(); b < size2; b++) {
              TLRPC.TL_secureFile secureFile=(TLRPC.TL_secureFile)secureValue.translation.get(b);
              byte[] decryptedSecret=decryptValueSecret(secureFile.secret,secureFile.file_hash);
              JSONObject file=new JSONObject();
              file.put("file_hash",Base64.encodeToString(secureFile.file_hash,Base64.NO_WRAP));
              file.put("secret",Base64.encodeToString(decryptedSecret,Base64.NO_WRAP));
              translation.put(file);
            }
            result.put("translation",translation);
          }
          jsonObject.put(getNameForType(secureValue.type),result);
        }
 catch (        Exception ignore) {
        }
      }
      TLRPC.TL_secureValueHash hash=new TLRPC.TL_secureValueHash();
      hash.type=secureValue.type;
      hash.hash=secureValue.hash;
      req.value_hashes.add(hash);
    }
    JSONObject result=new JSONObject();
    try {
      result.put("secure_data",jsonObject);
    }
 catch (    Exception ignore) {
    }
    if (currentPayload != null) {
      try {
        result.put("payload",currentPayload);
      }
 catch (      Exception ignore) {
      }
    }
    if (currentNonce != null) {
      try {
        result.put("nonce",currentNonce);
      }
 catch (      Exception ignore) {
      }
    }
    String json=result.toString();
    EncryptionResult encryptionResult=encryptData(AndroidUtilities.getStringBytes(json));
    req.credentials=new TLRPC.TL_secureCredentialsEncrypted();
    req.credentials.hash=encryptionResult.fileHash;
    req.credentials.data=encryptionResult.encryptedData;
    try {
      String key=currentPublicKey.replaceAll("\\n","").replace("-----BEGIN PUBLIC KEY-----","").replace("-----END PUBLIC KEY-----","");
      KeyFactory kf=KeyFactory.getInstance("RSA");
      X509EncodedKeySpec keySpecX509=new X509EncodedKeySpec(Base64.decode(key,Base64.DEFAULT));
      RSAPublicKey pubKey=(RSAPublicKey)kf.generatePublic(keySpecX509);
      Cipher c=Cipher.getInstance("RSA/NONE/OAEPWithSHA1AndMGF1Padding","BC");
      c.init(Cipher.ENCRYPT_MODE,pubKey);
      req.credentials.secret=c.doFinal(encryptionResult.decrypyedFileSecret);
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
    int reqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
      if (error == null) {
        ignoreOnFailure=true;
        callCallback(true);
        finishFragment();
      }
 else {
        showEditDoneProgress(false,false);
        if ("APP_VERSION_OUTDATED".equals(error.text)) {
          AlertsCreator.showUpdateAppAlert(getParentActivity(),LocaleController.getString("UpdateAppAlert",R.string.UpdateAppAlert),true);
        }
 else {
          showAlertWithText(LocaleController.getString("AppName",R.string.AppName),error.text);
        }
      }
    }
));
    ConnectionsManager.getInstance(currentAccount).bindRequestToGuid(reqId,classGuid);
  }
);
  acceptTextView=new TextView(context);
  acceptTextView.setCompoundDrawablePadding(AndroidUtilities.dp(8));
  acceptTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.authorize,0,0,0);
  acceptTextView.setTextColor(Theme.getColor(Theme.key_passport_authorizeText));
  acceptTextView.setText(LocaleController.getString("PassportAuthorize",R.string.PassportAuthorize));
  acceptTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,14);
  acceptTextView.setGravity(Gravity.CENTER);
  acceptTextView.setTypeface(AndroidUtilities.getTypeface("fonts/rmedium.ttf"));
  bottomLayout.addView(acceptTextView,LayoutHelper.createFrame(LayoutHelper.WRAP_CONTENT,LayoutHelper.MATCH_PARENT,Gravity.CENTER));
  progressViewButton=new ContextProgressView(context,0);
  progressViewButton.setVisibility(View.INVISIBLE);
  bottomLayout.addView(progressViewButton,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT));
  View shadow=new View(context);
  shadow.setBackgroundResource(R.drawable.header_shadow_reverse);
  frameLayout.addView(shadow,LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,3,Gravity.LEFT | Gravity.BOTTOM,0,0,0,48));
}
