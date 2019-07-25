private void update(Collection<BufferedElement> toRemove,StringBuilder fullContent,ARBufferedRegion lastAR){
  if (fullContent.length() > 0) {
    String content=fullContent.toString();
    String itemNameList=getItemNameList(content);
    lastAR.setTContent(itemNameList != null ? itemNameList : content);
    fullContent.setLength(0);
    toRemove.remove(lastAR);
  }
}
