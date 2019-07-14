/** 
 * Adds a user with the specified request json object.
 * @param requestJSONObject the specified request json object, for example,"userName": "", "userEmail": "", "userPassword": "", // Hashed "userLanguage": "", // optional, default to "zh_CN" "userAppRole": int, // optional, default to 0 "userRole": "", // optional, uses  {@value Role#ROLE_ID_C_DEFAULT} instead if not specified"userStatus": int, // optional, uses  {@value UserExt#USER_STATUS_C_NOT_VERIFIED} instead if not specified"userGuideStep": int, // optional, uses  {@value UserExt#USER_GUIDE_STEP_UPLOAD_AVATAR} instead if not specified"userAvatarURL": "" // optional, generate it if not specified ,see  {@link User} for more details
 * @return generated user id
 * @throws ServiceException if user name or email duplicated, or repository exception
 */
public synchronized String addUser(final JSONObject requestJSONObject) throws ServiceException {
  final Transaction transaction=userRepository.beginTransaction();
  try {
    final String userEmail=requestJSONObject.optString(User.USER_EMAIL).trim().toLowerCase();
    final String userName=requestJSONObject.optString(User.USER_NAME);
    JSONObject user=userRepository.getByName(userName);
    if (null != user && (UserExt.USER_STATUS_C_VALID == user.optInt(UserExt.USER_STATUS) || UserExt.USER_STATUS_C_INVALID_LOGIN == user.optInt(UserExt.USER_STATUS) || UserExt.USER_STATUS_C_DEACTIVATED == user.optInt(UserExt.USER_STATUS) || UserExt.NULL_USER_NAME.equals(userName))) {
      if (transaction.isActive()) {
        transaction.rollback();
      }
      throw new ServiceException(langPropsService.get("duplicatedUserNameLabel") + " [" + userName + "]");
    }
    boolean toUpdate=false;
    String ret=null;
    String avatarURL=null;
    user=userRepository.getByEmail(userEmail);
    int userNo=0;
    if (null != user) {
      if (UserExt.USER_STATUS_C_VALID == user.optInt(UserExt.USER_STATUS) || UserExt.USER_STATUS_C_INVALID_LOGIN == user.optInt(UserExt.USER_STATUS) || UserExt.USER_STATUS_C_DEACTIVATED == user.optInt(UserExt.USER_STATUS)) {
        if (transaction.isActive()) {
          transaction.rollback();
        }
        throw new ServiceException(langPropsService.get("duplicatedEmailLabel"));
      }
      toUpdate=true;
      ret=user.optString(Keys.OBJECT_ID);
      userNo=user.optInt(UserExt.USER_NO);
      avatarURL=user.optString(UserExt.USER_AVATAR_URL);
    }
    user=new JSONObject();
    user.put(User.USER_NAME,userName);
    user.put(User.USER_EMAIL,userEmail);
    user.put(UserExt.USER_APP_ROLE,requestJSONObject.optInt(UserExt.USER_APP_ROLE));
    user.put(User.USER_PASSWORD,requestJSONObject.optString(User.USER_PASSWORD));
    user.put(User.USER_ROLE,requestJSONObject.optString(User.USER_ROLE,Role.ROLE_ID_C_DEFAULT));
    user.put(User.USER_URL,"");
    user.put(UserExt.USER_ARTICLE_COUNT,0);
    user.put(UserExt.USER_COMMENT_COUNT,0);
    user.put(UserExt.USER_TAG_COUNT,0);
    user.put(UserExt.USER_INTRO,"");
    user.put(UserExt.USER_NICKNAME,"");
    user.put(UserExt.USER_AVATAR_TYPE,UserExt.USER_AVATAR_TYPE_C_UPLOAD);
    user.put(UserExt.USER_QQ,"");
    user.put(UserExt.USER_ONLINE_FLAG,false);
    user.put(UserExt.USER_LATEST_ARTICLE_TIME,0L);
    user.put(UserExt.USER_LATEST_CMT_TIME,0L);
    user.put(UserExt.USER_LATEST_LOGIN_TIME,0L);
    user.put(UserExt.USER_LATEST_LOGIN_IP,"");
    user.put(UserExt.USER_CHECKIN_TIME,0);
    user.put(UserExt.USER_CURRENT_CHECKIN_STREAK_START,0);
    user.put(UserExt.USER_CURRENT_CHECKIN_STREAK_END,0);
    user.put(UserExt.USER_LONGEST_CHECKIN_STREAK_START,0);
    user.put(UserExt.USER_LONGEST_CHECKIN_STREAK_END,0);
    user.put(UserExt.USER_LONGEST_CHECKIN_STREAK,0);
    user.put(UserExt.USER_CURRENT_CHECKIN_STREAK,0);
    user.put(UserExt.USER_POINT,0);
    user.put(UserExt.USER_USED_POINT,0);
    user.put(UserExt.USER_JOIN_POINT_RANK,UserExt.USER_JOIN_XXX_C_JOIN);
    user.put(UserExt.USER_JOIN_USED_POINT_RANK,UserExt.USER_JOIN_XXX_C_JOIN);
    user.put(UserExt.USER_TAGS,"");
    user.put(UserExt.USER_SKIN,Symphonys.SKIN_DIR_NAME);
    user.put(UserExt.USER_MOBILE_SKIN,Symphonys.MOBILE_SKIN_DIR_NAME);
    user.put(UserExt.USER_COUNTRY,"");
    user.put(UserExt.USER_PROVINCE,"");
    user.put(UserExt.USER_CITY,"");
    user.put(UserExt.USER_UPDATE_TIME,0L);
    user.put(UserExt.USER_GEO_STATUS,UserExt.USER_GEO_STATUS_C_PUBLIC);
    final int status=requestJSONObject.optInt(UserExt.USER_STATUS,UserExt.USER_STATUS_C_NOT_VERIFIED);
    user.put(UserExt.USER_STATUS,status);
    user.put(UserExt.USER_COMMENT_VIEW_MODE,UserExt.USER_COMMENT_VIEW_MODE_C_REALTIME);
    user.put(UserExt.USER_ONLINE_STATUS,UserExt.USER_XXX_STATUS_C_PUBLIC);
    user.put(UserExt.USER_ARTICLE_STATUS,UserExt.USER_XXX_STATUS_C_PUBLIC);
    user.put(UserExt.USER_COMMENT_STATUS,UserExt.USER_XXX_STATUS_C_PUBLIC);
    user.put(UserExt.USER_FOLLOWING_ARTICLE_STATUS,UserExt.USER_XXX_STATUS_C_PUBLIC);
    user.put(UserExt.USER_WATCHING_ARTICLE_STATUS,UserExt.USER_XXX_STATUS_C_PUBLIC);
    user.put(UserExt.USER_FOLLOWING_TAG_STATUS,UserExt.USER_XXX_STATUS_C_PUBLIC);
    user.put(UserExt.USER_FOLLOWING_USER_STATUS,UserExt.USER_XXX_STATUS_C_PUBLIC);
    user.put(UserExt.USER_FOLLOWER_STATUS,UserExt.USER_XXX_STATUS_C_PUBLIC);
    user.put(UserExt.USER_BREEZEMOON_STATUS,UserExt.USER_XXX_STATUS_C_PUBLIC);
    user.put(UserExt.USER_POINT_STATUS,UserExt.USER_XXX_STATUS_C_PUBLIC);
    user.put(UserExt.USER_UA_STATUS,UserExt.USER_XXX_STATUS_C_PUBLIC);
    user.put(UserExt.USER_NOTIFY_STATUS,UserExt.USER_XXX_STATUS_C_ENABLED);
    user.put(UserExt.USER_SUB_MAIL_STATUS,UserExt.USER_XXX_STATUS_C_ENABLED);
    user.put(UserExt.USER_LIST_PAGE_SIZE,Symphonys.ARTICLE_LIST_CNT);
    user.put(UserExt.USER_LIST_VIEW_MODE,UserExt.USER_LIST_VIEW_MODE_TITLE);
    user.put(UserExt.USER_AVATAR_VIEW_MODE,UserExt.USER_AVATAR_VIEW_MODE_C_ORIGINAL);
    user.put(UserExt.USER_SUB_MAIL_SEND_TIME,System.currentTimeMillis());
    user.put(UserExt.USER_KEYBOARD_SHORTCUTS_STATUS,UserExt.USER_XXX_STATUS_C_DISABLED);
    user.put(UserExt.USER_REPLY_WATCH_ARTICLE_STATUS,UserExt.USER_SUB_MAIL_STATUS_DISABLED);
    user.put(UserExt.USER_FORWARD_PAGE_STATUS,UserExt.USER_XXX_STATUS_C_ENABLED);
    user.put(UserExt.USER_INDEX_REDIRECT_URL,"");
    final JSONObject optionLanguage=optionRepository.get(Option.ID_C_MISC_LANGUAGE);
    final String adminSpecifiedLang=optionLanguage.optString(Option.OPTION_VALUE);
    if ("0".equals(adminSpecifiedLang)) {
      user.put(UserExt.USER_LANGUAGE,requestJSONObject.optString(UserExt.USER_LANGUAGE,"zh_CN"));
    }
 else {
      user.put(UserExt.USER_LANGUAGE,adminSpecifiedLang);
    }
    user.put(UserExt.USER_TIMEZONE,requestJSONObject.optString(UserExt.USER_TIMEZONE,"Asia/Shanghai"));
    user.put(UserExt.USER_GUIDE_STEP,requestJSONObject.optInt(UserExt.USER_GUIDE_STEP,UserExt.USER_GUIDE_STEP_UPLOAD_AVATAR));
    if (toUpdate) {
      user.put(UserExt.USER_NO,userNo);
      if (!AvatarQueryService.DEFAULT_AVATAR_URL.equals(avatarURL)) {
        if (Symphonys.QN_ENABLED) {
          user.put(UserExt.USER_AVATAR_URL,Symphonys.UPLOAD_QINIU_DOMAIN + "/avatar/" + ret + "?" + new Date().getTime());
        }
 else {
          user.put(UserExt.USER_AVATAR_URL,avatarURL + "?" + new Date().getTime());
        }
        avatarURL=user.optString(UserExt.USER_AVATAR_URL);
        if (255 < StringUtils.length(avatarURL)) {
          LOGGER.warn("Length of user [" + userName + "]'s avatar URL [" + avatarURL + "] larger then 255");
          avatarURL=AvatarQueryService.DEFAULT_AVATAR_URL;
          user.put(UserExt.USER_AVATAR_URL,avatarURL);
        }
        userRepository.update(ret,user);
      }
    }
 else {
      ret=Ids.genTimeMillisId();
      user.put(Keys.OBJECT_ID,ret);
      final String specifiedAvatar=requestJSONObject.optString(UserExt.USER_AVATAR_URL);
      if (AvatarQueryService.DEFAULT_AVATAR_URL.equals(specifiedAvatar)) {
        user.put(UserExt.USER_AVATAR_URL,specifiedAvatar);
      }
 else {
        try {
          byte[] avatarData;
          final String hash=DigestUtils.md5Hex(ret);
          avatarData=Gravatars.getRandomAvatarData(hash);
          if (null == avatarData) {
            final BufferedImage img=avatarQueryService.createAvatar(hash,512);
            final ByteArrayOutputStream baos=new ByteArrayOutputStream();
            ImageIO.write(img,"jpg",baos);
            baos.flush();
            avatarData=baos.toByteArray();
            baos.close();
          }
          if (Symphonys.QN_ENABLED) {
            final Auth auth=Auth.create(Symphonys.UPLOAD_QINIU_AK,Symphonys.UPLOAD_QINIU_SK);
            final UploadManager uploadManager=new UploadManager(new Configuration());
            uploadManager.put(avatarData,"avatar/" + ret,auth.uploadToken(Symphonys.UPLOAD_QINIU_BUCKET),null,"image/jpeg",false);
            user.put(UserExt.USER_AVATAR_URL,Symphonys.UPLOAD_QINIU_DOMAIN + "/avatar/" + ret + "?" + new Date().getTime());
          }
 else {
            String fileName=UUID.randomUUID().toString().replaceAll("-","") + ".jpg";
            fileName=FileUploadProcessor.genFilePath(fileName);
            new File(Symphonys.UPLOAD_LOCAL_DIR + fileName).getParentFile().mkdirs();
            try (final OutputStream output=new FileOutputStream(Symphonys.UPLOAD_LOCAL_DIR + fileName)){
              IOUtils.write(avatarData,output);
            }
             user.put(UserExt.USER_AVATAR_URL,Latkes.getServePath() + "/upload/" + fileName);
          }
        }
 catch (        final IOException e) {
          LOGGER.log(Level.ERROR,"Generates avatar error, using default thumbnail instead",e);
          user.put(UserExt.USER_AVATAR_URL,AvatarQueryService.DEFAULT_AVATAR_URL);
        }
      }
      final JSONObject memberCntOption=optionRepository.get(Option.ID_C_STATISTIC_MEMBER_COUNT);
      final int memberCount=memberCntOption.optInt(Option.OPTION_VALUE) + 1;
      user.put(UserExt.USER_NO,memberCount);
      userRepository.add(user);
      memberCntOption.put(Option.OPTION_VALUE,String.valueOf(memberCount));
      optionRepository.update(Option.ID_C_STATISTIC_MEMBER_COUNT,memberCntOption);
    }
    transaction.commit();
    if (UserExt.USER_STATUS_C_VALID == status) {
      pointtransferMgmtService.transfer(Pointtransfer.ID_C_SYS,ret,Pointtransfer.TRANSFER_TYPE_C_INIT,Pointtransfer.TRANSFER_SUM_C_INIT,ret,System.currentTimeMillis(),"");
      final Transaction trans=userRepository.beginTransaction();
      try {
        final Query query=new Query();
        final List<Filter> filters=new ArrayList<>();
        filters.add(new PropertyFilter(User.USER_NAME,FilterOperator.EQUAL,userName));
        filters.add(new PropertyFilter(UserExt.USER_STATUS,FilterOperator.EQUAL,UserExt.USER_STATUS_C_NOT_VERIFIED));
        query.setFilter(new CompositeFilter(CompositeFilterOperator.AND,filters));
        final JSONArray others=userRepository.get(query).optJSONArray(Keys.RESULTS);
        for (int i=0; i < others.length(); i++) {
          final JSONObject u=others.optJSONObject(i);
          final String id=u.optString(Keys.OBJECT_ID);
          final String mail=u.optString(User.USER_EMAIL);
          u.put(User.USER_NAME,UserExt.NULL_USER_NAME);
          u.put(User.USER_EMAIL,"");
          u.put(UserExt.USER_STATUS,UserExt.USER_STATUS_C_NOT_VERIFIED);
          userRepository.update(id,u);
          LOGGER.log(Level.INFO,"Defeated a user [email=" + mail + "]");
        }
        trans.commit();
      }
 catch (      final RepositoryException e) {
        if (trans.isActive()) {
          trans.rollback();
        }
        LOGGER.log(Level.ERROR,"Defeat others error",e);
      }
      final JSONObject notification=new JSONObject();
      notification.put(Notification.NOTIFICATION_USER_ID,ret);
      notification.put(Notification.NOTIFICATION_DATA_ID,"");
      notificationMgmtService.addSysAnnounceNewUserNotification(notification);
      final JSONObject u=new JSONObject();
      u.put(User.USER_NAME,user.optString(User.USER_NAME));
      u.put(UserExt.USER_T_NAME_LOWER_CASE,user.optString(User.USER_NAME).toLowerCase());
      final String avatar=avatarQueryService.getAvatarURLByUser(user,"20");
      u.put(UserExt.USER_AVATAR_URL,avatar);
      UserQueryService.USER_NAMES.add(u);
      Collections.sort(UserQueryService.USER_NAMES,(u1,u2) -> {
        final String u1Name=u1.optString(UserExt.USER_T_NAME_LOWER_CASE);
        final String u2Name=u2.optString(UserExt.USER_T_NAME_LOWER_CASE);
        return u1Name.compareTo(u2Name);
      }
);
    }
    return ret;
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Adds a user failed",e);
    throw new ServiceException(e);
  }
}
