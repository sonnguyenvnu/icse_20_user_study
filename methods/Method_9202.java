private float rubberYPoisition(float offset,float factor){
  float delta=Math.abs(offset);
  return (-((1.0f - (1.0f / ((delta * 0.55f / factor) + 1.0f))) * factor)) * (offset < 0.0f ? 1.0f : -1.0f);
}
