private void start(){
  DefaultListModel<MailMessage> listModel=(DefaultListModel)list1.getModel();
  listModel.removeAllElements();
  for (  ProfilerRequestInterface profilerRequest : this.profilerIndex.getRequests()) {
    MailCollectorInterface collector=profilerRequest.getCollector(MailCollectorInterface.class);
    if (collector == null) {
      continue;
    }
    Collection<MailMessage> messages=collector.getMessages();
    if (messages.size() > 0) {
      for (      MailMessage message : messages) {
        listModel.addElement(message);
      }
    }
  }
}
