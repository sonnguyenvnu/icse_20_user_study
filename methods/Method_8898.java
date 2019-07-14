public ArrayList<TLRPC.InputDocument> getMasks(){
  ArrayList<TLRPC.InputDocument> result=null;
  int count=entitiesView.getChildCount();
  for (int a=0; a < count; a++) {
    View child=entitiesView.getChildAt(a);
    if (child instanceof StickerView) {
      TLRPC.Document document=((StickerView)child).getSticker();
      if (result == null) {
        result=new ArrayList<>();
      }
      TLRPC.TL_inputDocument inputDocument=new TLRPC.TL_inputDocument();
      inputDocument.id=document.id;
      inputDocument.access_hash=document.access_hash;
      inputDocument.file_reference=document.file_reference;
      if (inputDocument.file_reference == null) {
        inputDocument.file_reference=new byte[0];
      }
      result.add(inputDocument);
    }
  }
  return result;
}
