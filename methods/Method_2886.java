boolean is_non_projective(){
  for (int modifier=0; modifier < heads.size(); ++modifier) {
    int head=heads.get(modifier);
    if (head < modifier) {
      for (int from=head + 1; from < modifier; ++from) {
        int to=heads.get(from);
        if (to < head || to > modifier) {
          return true;
        }
      }
    }
 else {
      for (int from=modifier + 1; from < head; ++from) {
        int to=heads.get(from);
        if (to < modifier || to > head) {
          return true;
        }
      }
    }
  }
  return false;
}
