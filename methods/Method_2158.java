/** 
 * Gets the original image bounds, in view-absolute coordinates. <p> The original image bounds are those reported by the hierarchy. The hierarchy itself may apply scaling on its own (e.g. due to scale type) so the reported bounds are not necessarily the same as the actual bitmap dimensions. In other words, the original image bounds correspond to the image bounds within this view when no zoomable transformation is applied, but including the potential scaling of the hierarchy. Having the actual bitmap dimensions abstracted away from this view greatly simplifies implementation because the actual bitmap may change (e.g. when a high-res image arrives and replaces the previously set low-res image). With proper hierarchy scaling (e.g. FIT_CENTER), this underlying change will not affect this view nor the zoomable transformation in any way.
 */
protected void getImageBounds(RectF outBounds){
  getHierarchy().getActualImageBounds(outBounds);
}
