public void selected(@NotNull ProfilerRequestInterface profilerRequest){
  MailCollectorInterface collector=profilerRequest.getCollector(MailCollectorInterface.class);
  if (collector != null) {
    Collection<MailMessage> messages=collector.getMessages();
    if (messages.size() > 0) {
      this.editorPane1.setText(messages.iterator().next().getMessage());
    }
  }
}
