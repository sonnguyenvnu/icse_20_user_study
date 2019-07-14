/** 
 * Evaluates the expression and adds the result to the item update.
 * @param item the item update where the term should be stored
 * @param ctxt the evaluation context for the expression
 */
public void contributeTo(ItemUpdateBuilder item,ExpressionContext ctxt){
  try {
    MonolingualTextValue val=getValue().evaluate(ctxt);
switch (getType()) {
case LABEL:
      item.addLabel(val,true);
    break;
case LABEL_IF_NEW:
  item.addLabel(val,false);
break;
case DESCRIPTION:
item.addDescription(val,true);
break;
case DESCRIPTION_IF_NEW:
item.addDescription(val,false);
break;
case ALIAS:
item.addAlias(val);
break;
}
}
 catch (SkipSchemaExpressionException e) {
return;
}
}
