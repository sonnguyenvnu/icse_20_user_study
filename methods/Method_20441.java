private void initTouch(final RecyclerView recyclerView){
  EpoxyTouchHelper.initDragging(controller).withRecyclerView(recyclerView).forVerticalList().withTarget(CarouselModelGroup.class).andCallbacks(new DragCallbacks<CarouselModelGroup>(){
    @Override public void onModelMoved(    int fromPosition,    int toPosition,    CarouselModelGroup modelBeingMoved,    View itemView){
      int carouselIndex=carousels.indexOf(modelBeingMoved.data);
      carousels.add(carouselIndex + (toPosition - fromPosition),carousels.remove(carouselIndex));
    }
    @Override public void onDragStarted(    CarouselModelGroup model,    View itemView,    int adapterPosition){
      backgroundAnimator=ValueAnimator.ofObject(new ArgbEvaluator(),Color.WHITE,selectedBackgroundColor);
      backgroundAnimator.addUpdateListener(animator -> itemView.setBackgroundColor((int)animator.getAnimatedValue()));
      backgroundAnimator.start();
      itemView.animate().scaleX(1.05f).scaleY(1.05f);
    }
    @Override public void onDragReleased(    CarouselModelGroup model,    View itemView){
      if (backgroundAnimator != null) {
        backgroundAnimator.cancel();
      }
      backgroundAnimator=ofObject(new ArgbEvaluator(),((ColorDrawable)itemView.getBackground()).getColor(),Color.WHITE);
      backgroundAnimator.addUpdateListener(animator -> itemView.setBackgroundColor((int)animator.getAnimatedValue()));
      backgroundAnimator.start();
      itemView.animate().scaleX(1f).scaleY(1f);
    }
    @Override public void clearView(    CarouselModelGroup model,    View itemView){
      onDragReleased(model,itemView);
    }
  }
);
}
