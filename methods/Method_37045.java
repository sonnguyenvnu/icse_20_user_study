protected void parseCard(Card card,JSONObject data,ServiceManager serviceManager,Map<String,ComponentInfo> componentInfoMap){
  card.id=data.getString(KEY_ID);
  if (card.id == null) {
    card.id="";
  }
  JSONObject header=data.getJSONObject(KEY_HEADER);
  BaseCell headerCell=parseSingleComponent(header,card,serviceManager,componentInfoMap);
  parseHeaderCell(headerCell,card);
  JSONArray componentArray=data.getJSONArray(KEY_ITEMS);
  if (componentArray != null) {
    final int cellLength=componentArray.size();
    for (int i=0; i < cellLength; i++) {
      final JSONObject cellData=componentArray.getJSONObject(i);
      parseSingleComponent(cellData,card,card.serviceManager,componentInfoMap);
    }
  }
  JSONObject footer=data.getJSONObject(KEY_FOOTER);
  BaseCell footerCell=parseSingleComponent(footer,card,serviceManager,componentInfoMap);
  parseFooterCell(footerCell,card);
}
