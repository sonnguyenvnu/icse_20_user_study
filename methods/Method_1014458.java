@Override protected void service(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException {
  for (  Object key : req.getParameterMap().keySet()) {
    String itemName=key.toString();
    if (!itemName.startsWith("__")) {
      String commandName=req.getParameter(itemName);
      try {
        Item item=itemRegistry.getItem(itemName);
        if ((item instanceof SwitchItem || item instanceof GroupItem) && commandName.equals("TOGGLE")) {
          commandName=OnOffType.ON.equals(item.getStateAs(OnOffType.class)) ? "OFF" : "ON";
        }
        Command command=TypeParser.parseCommand(item.getAcceptedCommandTypes(),commandName);
        if (command != null) {
          eventPublisher.post(ItemEventFactory.createCommandEvent(itemName,command));
        }
 else {
          logger.warn("Received unknown command '{}' for item '{}'",commandName,itemName);
        }
      }
 catch (      ItemNotFoundException e) {
        logger.warn("Received command '{}' for item '{}', but the item does not exist in the registry",commandName,itemName);
      }
    }
  }
}
