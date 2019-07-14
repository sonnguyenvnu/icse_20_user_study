private static List<StickerBuilder> getStickerBuilders(Context context) throws IOException {
  List<StickerBuilder> stickerBuilders=new ArrayList<>();
  int[] stickerColors=new int[]{Color.GREEN,Color.RED,Color.BLUE,Color.YELLOW,Color.MAGENTA};
  File stickersDir=new File(context.getFilesDir(),"stickers");
  if (!stickersDir.exists() && !stickersDir.mkdirs()) {
    throw new IOException("Stickers directory does not exist");
  }
  for (int i=0; i < stickerColors.length; i++) {
    String stickerFilename=getStickerFilename(i);
    File stickerFile=new File(stickersDir,stickerFilename);
    String imageUrl=getStickerUrl(stickerFilename);
    writeSolidColorBitmapToFile(stickerFile,stickerColors[i]);
    StickerBuilder stickerBuilder=Indexables.stickerBuilder().setName(getStickerFilename(i)).setUrl(String.format(STICKER_URL_PATTERN,i)).setImage(imageUrl).setDescription("content description").setIsPartOf(Indexables.stickerPackBuilder().setName(STICKER_PACK_NAME)).put("keywords","tag1","tag2");
    stickerBuilders.add(stickerBuilder);
  }
  return stickerBuilders;
}
