private static List<Indexable> getIndexableStickers(Context context) throws IOException, FirebaseAppIndexingInvalidArgumentException {
  List<Indexable> indexableStickers=new ArrayList<>();
  List<StickerBuilder> stickerBuilders=getStickerBuilders(context);
  for (  StickerBuilder stickerBuilder : stickerBuilders) {
    stickerBuilder.setIsPartOf(Indexables.stickerPackBuilder().setName(STICKER_PACK_NAME)).put("keywords","tag1","tag2");
    indexableStickers.add(stickerBuilder.build());
  }
  return indexableStickers;
}
