public SMILElement getBody(){
  Node rootElement=getDocumentElement();
  Node headElement=getHead();
  Node bodyElement=headElement.getNextSibling();
  if (bodyElement == null || !(bodyElement instanceof SMILElement)) {
    bodyElement=createElement("body");
    rootElement.appendChild(bodyElement);
  }
  mSeqTimeContainer=new ElementSequentialTimeContainerImpl((SMILElement)bodyElement){
    public NodeList getTimeChildren(){
      return getBody().getElementsByTagName("par");
    }
    public boolean beginElement(){
      Event startEvent=createEvent("Event");
      startEvent.initEvent(SMIL_DOCUMENT_START_EVENT,false,false);
      dispatchEvent(startEvent);
      return true;
    }
    public boolean endElement(){
      Event endEvent=createEvent("Event");
      endEvent.initEvent(SMIL_DOCUMENT_END_EVENT,false,false);
      dispatchEvent(endEvent);
      return true;
    }
    public void pauseElement(){
    }
    public void resumeElement(){
    }
    public void seekElement(    float seekTo){
    }
    ElementTime getParentElementTime(){
      return null;
    }
  }
;
  return (SMILElement)bodyElement;
}
