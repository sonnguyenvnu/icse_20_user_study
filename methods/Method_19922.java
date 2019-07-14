private static Indexable getIndexableStickerPack(Context context) throws IOException, FirebaseAppIndexingInvalidArgumentException {
  List<StickerBuilder> stickerBuilders=getStickerBuilders(context);
  File stickersDir=new File(context.getFilesDir(),"stickers");
  if (!stickersDir.exists() && !stickersDir.mkdirs()) {
    throw new IOException("Stickers directory does not exist");
  }
  final int lastIndex=stickerBuilders.size() - 1;
  final String stickerName=getStickerFilename(lastIndex);
  final String imageUrl=getStickerUrl(stickerName);
  StickerPackBuilder stickerPackBuilder=Indexables.stickerPackBuilder().setName(STICKER_PACK_NAME).setUrl(String.format(STICKER_PACK_URL_PATTERN,lastIndex)).setImage(imageUrl).setHasSticker(stickerBuilders.toArray(new StickerBuilder[stickerBuilders.size()])).setDescription("content description");
  return stickerPackBuilder.build();
}
