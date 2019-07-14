private static int getPosition(@NonNull EpoxyController controller,@NonNull EpoxyModel<?> model){
  if (controller.isBuildingModels()) {
    return controller.getFirstIndexOfModelInBuildingList(model);
  }
  return controller.getAdapter().getModelPosition(model);
}
