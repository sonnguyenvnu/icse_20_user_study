public float percentageFunded(){
  if (goal() > 0.0f) {
    return ((float)pledged() / (float)goal()) * 100.0f;
  }
  return 0.0f;
}
