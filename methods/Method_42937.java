public List<BiboxAsset> getBiboxAccountInfo(){
  try {
    BiboxSingleResponse<BiboxAssetsResult> response=bibox.assets(ASSETS_CMD.json(),apiKey,signatureCreator);
    throwErrors(response);
    return response.get().getResult().getAssets_list();
  }
 catch (  BiboxException e) {
    throw new ExchangeException(e.getMessage());
  }
}
