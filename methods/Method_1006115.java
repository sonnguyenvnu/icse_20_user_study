@Override public void changed(ObservableValue<? extends State> observable,State oldValue,State newValue){
  if (State.SUCCEEDED.equals(newValue)) {
    Document document=webView.getEngine().getDocument();
    NodeList anchors=document.getElementsByTagName(ANCHOR_TAG);
    for (int i=0; i < anchors.getLength(); i++) {
      Node node=anchors.item(i);
      EventTarget eventTarget=(EventTarget)node;
      eventTarget.addEventListener(CLICK_EVENT,this,false);
    }
  }
}
