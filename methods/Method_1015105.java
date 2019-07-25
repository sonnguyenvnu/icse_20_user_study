public static void init(Context context,String stickerPath){
  getAndSaveParameter(context);
  STICKER_PATH=stickerPath;
  copyStickerToStickerPath(STICKER_NAME_IN_ASSETS);
}
