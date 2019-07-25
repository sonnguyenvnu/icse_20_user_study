public CommandExecutionResult swimlane(String name,HtmlColor color,Display label){
  if (swimlaneStrategy == null) {
    swimlaneStrategy=SwimlaneStrategy.SWIMLANE_ALLOWED;
  }
  if (swimlaneStrategy == SwimlaneStrategy.SWIMLANE_FORBIDDEN) {
    return CommandExecutionResult.error("This swimlane must be defined at the start of the diagram.");
  }
  swinlanes.swimlane(name,color,label);
  return CommandExecutionResult.ok();
}
