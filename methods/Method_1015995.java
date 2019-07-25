public WebDocument store(Index index) throws IOException {
  if (index == null)   return this;
  index.add(GridIndex.WEB_INDEX_NAME,"crawler",getId(),this);
  return this;
}
