public void process(){
  IDocumentFormatter formatter=handler.getFormatter();
  if (formatter == null) {
    return;
  }
  if (handler.hasSharedContext()) {
    String id=idAttribute.getValue();
    if (!StringUtils.isEmpty(type)) {
      return;
    }
    String content="";
    if (isContainsField()) {
      content=super.getInnerText();
      super.setInnerText(formatter.formatAsSimpleField(true,true,NoteInfo.CONTEXT_KEY,NoteInfo.CONTENT_PROPERTY));
    }
    InitialNoteInfoMap infos=getInitialNoteInfoMap(handler.getSharedContext());
    if (infos == null) {
      infos=new InitialNoteInfoMap();
      putInitialNoteInfoMap(handler.getSharedContext(),infos);
    }
    NoteInfo info=new NoteInfo(id,content);
    infos.put(id,info);
    String newId=formatter.formatAsSimpleField(true,NoteInfo.CONTEXT_KEY,NoteInfo.ID_PROPERTY);
    idAttribute.setValue(newId);
    String listName=formatter.getFunctionDirective(false,getNoteRegistryKey(),NoteRegistry.GET_NOTES_METHOD,"'" + id + "'");
    String before=formatter.getStartLoopDirective(NoteInfo.CONTEXT_KEY,listName);
    this.setContentBeforeStartTagElement(before);
    String after=formatter.getEndLoopDirective("");
    this.setContentAfterEndTagElement(after);
  }
}
