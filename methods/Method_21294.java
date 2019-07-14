private void flipIndicatorIfStatsOffScreen(final View indicator,final View stats){
  stats.post(() -> {
    final int leftVisibleEdgeOfBreakdownView=this.referrerBreakdownLayout.getLeft() + this.referrerBreakdownLayout.getPaddingLeft();
    if (stats.getLeft() < leftVisibleEdgeOfBreakdownView) {
      indicator.setScaleX(-1);
      final ConstraintLayout.LayoutParams indicatorLayoutParams=(ConstraintLayout.LayoutParams)indicator.getLayoutParams();
      indicatorLayoutParams.setMarginStart(this.grid3Pixels);
      indicator.setLayoutParams(indicatorLayoutParams);
      final ConstraintLayout.LayoutParams statsLayoutParams=(ConstraintLayout.LayoutParams)stats.getLayoutParams();
      statsLayoutParams.startToEnd=indicator.getId();
      statsLayoutParams.setMarginStart(this.grid1Pixels);
      statsLayoutParams.endToStart=ConstraintLayout.LayoutParams.UNSET;
      stats.setLayoutParams(statsLayoutParams);
    }
  }
);
}
