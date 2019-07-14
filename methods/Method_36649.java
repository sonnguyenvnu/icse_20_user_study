private void transformCardCellData(JSONObject cardData){
  try {
    if (cardData.has("iconList")) {
      cardData.put(Card.KEY_ITEMS,cardData.getJSONArray("iconList"));
    }
 else     if (cardData.has("centerBannerList")) {
      cardData.put(Card.KEY_ITEMS,cardData.getJSONArray("centerBannerList"));
    }
  }
 catch (  JSONException e) {
    e.printStackTrace();
  }
}
