public void updateConfig(final TLRPC.TL_config config){
  AndroidUtilities.runOnUIThread(() -> {
    DownloadController.getInstance(currentAccount).loadAutoDownloadConfig(false);
    maxMegagroupCount=config.megagroup_size_max;
    maxGroupCount=config.chat_size_max;
    maxEditTime=config.edit_time_limit;
    ratingDecay=config.rating_e_decay;
    maxRecentGifsCount=config.saved_gifs_limit;
    maxRecentStickersCount=config.stickers_recent_limit;
    maxFaveStickersCount=config.stickers_faved_limit;
    revokeTimeLimit=config.revoke_time_limit;
    revokeTimePmLimit=config.revoke_pm_time_limit;
    canRevokePmInbox=config.revoke_pm_inbox;
    linkPrefix=config.me_url_prefix;
    if (linkPrefix.endsWith("/")) {
      linkPrefix=linkPrefix.substring(0,linkPrefix.length() - 1);
    }
    if (linkPrefix.startsWith("https://")) {
      linkPrefix=linkPrefix.substring(8);
    }
 else     if (linkPrefix.startsWith("http://")) {
      linkPrefix=linkPrefix.substring(7);
    }
    callReceiveTimeout=config.call_receive_timeout_ms;
    callRingTimeout=config.call_ring_timeout_ms;
    callConnectTimeout=config.call_connect_timeout_ms;
    callPacketTimeout=config.call_packet_timeout_ms;
    maxPinnedDialogsCount=config.pinned_dialogs_count_max;
    maxFolderPinnedDialogsCount=config.pinned_infolder_count_max;
    maxMessageLength=config.message_length_max;
    maxCaptionLength=config.caption_length_max;
    defaultP2pContacts=config.default_p2p_contacts;
    preloadFeaturedStickers=config.preload_featured_stickers;
    if (config.venue_search_username != null) {
      venueSearchBot=config.venue_search_username;
    }
    if (config.gif_search_username != null) {
      gifSearchBot=config.gif_search_username;
    }
    if (imageSearchBot != null) {
      imageSearchBot=config.img_search_username;
    }
    blockedCountry=config.blocked_mode;
    dcDomainName=config.dc_txt_domain_name;
    webFileDatacenterId=config.webfile_dc_id;
    if (suggestedLangCode == null || !suggestedLangCode.equals(config.suggested_lang_code)) {
      suggestedLangCode=config.suggested_lang_code;
      LocaleController.getInstance().loadRemoteLanguages(currentAccount);
    }
    if (config.static_maps_provider == null) {
      config.static_maps_provider="google";
    }
    mapKey=null;
    mapProvider=0;
    availableMapProviders=0;
    String[] providers=config.static_maps_provider.split(",");
    for (int a=0; a < providers.length; a++) {
      String[] mapArgs=providers[a].split("\\+");
      if (mapArgs.length > 0) {
        String[] typeAndKey=mapArgs[0].split(":");
        if (typeAndKey.length > 0) {
          if ("yandex".equals(typeAndKey[0])) {
            if (a == 0) {
              if (mapArgs.length > 1) {
                mapProvider=3;
              }
 else {
                mapProvider=1;
              }
            }
            availableMapProviders|=4;
          }
 else           if ("google".equals(typeAndKey[0])) {
            if (a == 0) {
              if (mapArgs.length > 1) {
                mapProvider=4;
              }
            }
            availableMapProviders|=1;
          }
 else           if ("telegram".equals(typeAndKey[0])) {
            if (a == 0) {
              mapProvider=2;
            }
            availableMapProviders|=2;
          }
          if (typeAndKey.length > 1) {
            mapKey=typeAndKey[1];
          }
        }
      }
    }
    SharedPreferences.Editor editor=mainPreferences.edit();
    editor.putInt("maxGroupCount",maxGroupCount);
    editor.putInt("maxMegagroupCount",maxMegagroupCount);
    editor.putInt("maxEditTime",maxEditTime);
    editor.putInt("ratingDecay",ratingDecay);
    editor.putInt("maxRecentGifsCount",maxRecentGifsCount);
    editor.putInt("maxRecentStickersCount",maxRecentStickersCount);
    editor.putInt("maxFaveStickersCount",maxFaveStickersCount);
    editor.putInt("callReceiveTimeout",callReceiveTimeout);
    editor.putInt("callRingTimeout",callRingTimeout);
    editor.putInt("callConnectTimeout",callConnectTimeout);
    editor.putInt("callPacketTimeout",callPacketTimeout);
    editor.putString("linkPrefix",linkPrefix);
    editor.putInt("maxPinnedDialogsCount",maxPinnedDialogsCount);
    editor.putInt("maxFolderPinnedDialogsCount",maxFolderPinnedDialogsCount);
    editor.putInt("maxMessageLength",maxMessageLength);
    editor.putInt("maxCaptionLength",maxCaptionLength);
    editor.putBoolean("defaultP2pContacts",defaultP2pContacts);
    editor.putBoolean("preloadFeaturedStickers",preloadFeaturedStickers);
    editor.putInt("revokeTimeLimit",revokeTimeLimit);
    editor.putInt("revokeTimePmLimit",revokeTimePmLimit);
    editor.putInt("mapProvider",mapProvider);
    if (mapKey != null) {
      editor.putString("pk",mapKey);
    }
 else {
      editor.remove("pk");
    }
    editor.putBoolean("canRevokePmInbox",canRevokePmInbox);
    editor.putBoolean("blockedCountry",blockedCountry);
    editor.putString("venueSearchBot",venueSearchBot);
    editor.putString("gifSearchBot",gifSearchBot);
    editor.putString("imageSearchBot",imageSearchBot);
    editor.putString("dcDomainName",dcDomainName);
    editor.putInt("webFileDatacenterId",webFileDatacenterId);
    editor.putString("suggestedLangCode",suggestedLangCode);
    editor.commit();
    LocaleController.getInstance().checkUpdateForCurrentRemoteLocale(currentAccount,config.lang_pack_version,config.base_lang_pack_version);
    NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.configLoaded);
  }
);
}
