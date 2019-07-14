/** 
 * {@inheritDoc}
 */
@NonNull @Override public BaseCell parseSingleComponent(@Nullable JSONObject data,Card parent,ServiceManager serviceManager){
  if (data == null) {
    return BaseCell.NaN;
  }
  final CardResolver cardResolver=serviceManager.getService(CardResolver.class);
  Preconditions.checkState(cardResolver != null,"Must register CardResolver into ServiceManager first");
  final MVHelper cellResolver=serviceManager.getService(MVHelper.class);
  Preconditions.checkState(cellResolver != null,"Must register CellResolver into ServiceManager first");
  BaseCell cell=Card.createCell(parent,cellResolver,data,serviceManager,true);
  if (cellResolver.isValid(cell,serviceManager)) {
    return cell;
  }
 else {
    return BaseCell.NaN;
  }
}
