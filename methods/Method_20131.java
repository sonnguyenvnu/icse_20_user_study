/** 
 * Set the number of views to show on screen in this carousel at a time, partial numbers are allowed. <p>This is useful where you want to easily control for the number of items on screen, regardless of screen size. For example, you could set this to 1.2f so that one view is shown in full and 20% of the next view "peeks" from the edge to indicate that there is more content to scroll to. <p>Another pattern is setting a different view count depending on whether the device is phone or tablet. <p>Additionally, if a LinearLayoutManager is used this value will be forwarded to  {@link LinearLayoutManager#setInitialPrefetchItemCount(int)} as a performance optimization.<p>If you want to only change the prefetch count without changing the view size you can simply use  {@link #setInitialPrefetchItemCount(int)}
 */
@ModelProp(group="prefetch") public void setNumViewsToShowOnScreen(float viewCount){
  numViewsToShowOnScreen=viewCount;
  setInitialPrefetchItemCount((int)Math.ceil(viewCount));
}
