public void createHeaders(boolean invalidate,List<IconDataParcelable> uris){
  boolean[] headers=new boolean[]{false,false};
  for (int i=0; i < itemsDigested.size(); i++) {
    if (itemsDigested.get(i).elem != null) {
      LayoutElementParcelable nextItem=itemsDigested.get(i).elem;
      if (!headers[0] && nextItem.isDirectory) {
        headers[0]=true;
        itemsDigested.add(i,new ListItem(TYPE_HEADER_FOLDERS));
        uris.add(i,null);
        continue;
      }
      if (!headers[1] && !nextItem.isDirectory && !nextItem.title.equals(".") && !nextItem.title.equals("..")) {
        headers[1]=true;
        itemsDigested.add(i,new ListItem(TYPE_HEADER_FILES));
        uris.add(i,null);
        continue;
      }
    }
  }
  if (invalidate) {
    notifyDataSetChanged();
  }
}
