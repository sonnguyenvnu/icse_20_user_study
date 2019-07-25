@Command(name="/calculate",aliases={"/calc","/eval","/evaluate","/solve"},desc="Evaluate a mathematical expression") @CommandPermissions("worldedit.calc") public void calc(Actor actor,@Arg(desc="Expression to evaluate",variable=true) List<String> input){
  Expression expression;
  try {
    expression=Expression.compile(String.join(" ",input));
  }
 catch (  ExpressionException e) {
    actor.printError(String.format("'%s' could not be parsed as a valid expression",input));
    return;
  }
  WorldEditAsyncCommandBuilder.createAndSendMessage(actor,() -> {
    double result=expression.evaluate(new double[]{},WorldEdit.getInstance().getSessionManager().get(actor).getTimeout());
    String formatted=Double.isNaN(result) ? "NaN" : formatter.format(result);
    return SubtleFormat.wrap(input + " = ").append(TextComponent.of(formatted,TextColor.LIGHT_PURPLE));
  }
,null);
}
