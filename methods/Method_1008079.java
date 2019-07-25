@Override void analyze(Locals locals){
  array=locals.addVariable(location,expression.actual,"#array" + location.getOffset(),true);
  index=locals.addVariable(location,locals.getDefinition().intType,"#index" + location.getOffset(),true);
  indexed=locals.getDefinition().getType(expression.actual.struct,expression.actual.dimensions - 1);
  cast=locals.getDefinition().caster.getLegalCast(location,indexed,variable.type,true,true);
}
