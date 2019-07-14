/** 
 * Sets the algorithm used to calculate two-dimensional interpolation. <p> Transitions such as  {@link ChangeBounds} move Views, typicallyin a straight path between the start and end positions. Applications that desire to have these motions move in a curve can change how Views interpolate in two dimensions by extending PathMotion and implementing {@link PathMotion#getPath(float,float,float,float)}. </p> <p> When describing in XML, use a nested XML tag for the path motion. It can be one of the built-in tags <code>arcMotion</code> or <code>patternPathMotion</code> or it can be a custom PathMotion using <code>pathMotion</code> with the <code>class</code> attributed with the fully-described class name. For example:</p> <pre> {@code &lt;changeBounds> &lt;pathMotion class="my.app.transition.MyPathMotion"/> &lt;/changeBounds>}</pre> <p>or</p> <pre> {@code &lt;changeBounds> &lt;arcMotion android:minimumHorizontalAngle="15" android:minimumVerticalAngle="0" android:maximumAngle="90"/> &lt;/changeBounds>}</pre>
 * @param pathMotion Algorithm object to use for determining how to interpolate in twodimensions. If null, a straight-path algorithm will be used.
 * @return This transition object.
 * @see ArcMotion
 * @see PatternPathMotion
 * @see PathMotion
 */
@NonNull public Transition setPathMotion(@Nullable PathMotion pathMotion){
  if (pathMotion == null) {
    mPathMotion=PathMotion.STRAIGHT_PATH_MOTION;
  }
 else {
    mPathMotion=pathMotion;
  }
  return this;
}
