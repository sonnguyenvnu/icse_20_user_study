int cost(List<Integer> gold_heads,List<Integer> gold_deprels){
  List<List<Integer>> tree=new ArrayList<List<Integer>>(gold_heads.size());
  for (int i=0; i < gold_heads.size(); ++i) {
    int h=gold_heads.get(i);
    if (h >= 0) {
      tree.get(h).add(i);
    }
  }
  List<Integer> sigma_l=stack;
  List<Integer> sigma_r=new ArrayList<Integer>();
  sigma_r.add(stack.get(stack.size() - 1));
  boolean[] sigma_l_mask=new boolean[gold_heads.size()];
  boolean[] sigma_r_mask=new boolean[gold_heads.size()];
  for (int s=0; s < sigma_l.size(); ++s) {
    sigma_l_mask[sigma_l.get(s)]=true;
  }
  for (int i=buffer; i < ref.size(); ++i) {
    if (gold_heads.get(i) < buffer) {
      sigma_r.add(i);
      sigma_r_mask[i]=true;
      continue;
    }
    List<Integer> node=tree.get(i);
    for (int d=0; d < node.size(); ++d) {
      if (sigma_l_mask[node.get(d)] || sigma_r_mask[node.get(d)]) {
        sigma_r.add(i);
        sigma_r_mask[i]=true;
        break;
      }
    }
  }
  int len_l=sigma_l.size();
  int len_r=sigma_r.size();
  int[][][] T=new int[len_l][len_r][len_l + len_r - 1];
  for (  int[][] one : T) {
    for (    int[] two : one) {
      for (int i=0; i < two.length; i++) {
        two[i]=1024;
      }
    }
  }
  T[0][0][len_l - 1]=0;
  for (int d=0; d < len_l + len_r - 1; ++d) {
    for (int j=Math.max(0,d - len_l + 1); j < Math.min(d + 1,len_r); ++j) {
      int i=d - j;
      if (i < len_l - 1) {
        int i_1=sigma_l.get(len_l - i - 2);
        int i_1_rank=len_l - i - 2;
        for (int rank=len_l - i - 1; rank < len_l; ++rank) {
          int h=sigma_l.get(rank);
          int h_rank=rank;
          T[i + 1][j][h_rank]=Math.min(T[i + 1][j][h_rank],T[i][j][h_rank] + (gold_heads.get(i_1) == h ? 0 : 2));
          T[i + 1][j][i_1_rank]=Math.min(T[i + 1][j][i_1_rank],T[i][j][h_rank] + (gold_heads.get(h) == i_1 ? 0 : 2));
        }
        for (int rank=1; rank < j + 1; ++rank) {
          int h=sigma_r.get(rank);
          int h_rank=len_l + rank - 1;
          T[i + 1][j][h_rank]=Math.min(T[i + 1][j][h_rank],T[i][j][h_rank] + (gold_heads.get(i_1) == h ? 0 : 2));
          T[i + 1][j][i_1_rank]=Math.min(T[i + 1][j][i_1_rank],T[i][j][h_rank] + (gold_heads.get(h) == i_1 ? 0 : 2));
        }
      }
      if (j < len_r - 1) {
        int j_1=sigma_r.get(j + 1);
        int j_1_rank=len_l + j;
        for (int rank=len_l - i - 1; rank < len_l; ++rank) {
          int h=sigma_l.get(rank);
          int h_rank=rank;
          T[i][j + 1][h_rank]=Math.min(T[i][j + 1][h_rank],T[i][j][h_rank] + (gold_heads.get(j_1) == h ? 0 : 2));
          T[i][j + 1][j_1_rank]=Math.min(T[i][j + 1][j_1_rank],T[i][j][h_rank] + (gold_heads.get(h) == j_1 ? 0 : 2));
        }
        for (int rank=1; rank < j + 1; ++rank) {
          int h=sigma_r.get(rank);
          int h_rank=len_l + rank - 1;
          T[i][j + 1][h_rank]=Math.min(T[i][j + 1][h_rank],T[i][j][h_rank] + (gold_heads.get(j_1) == h ? 0 : 2));
          T[i][j + 1][j_1_rank]=Math.min(T[i][j + 1][j_1_rank],T[i][j][h_rank] + (gold_heads.get(h) == j_1 ? 0 : 2));
        }
      }
    }
  }
  int penalty=0;
  for (int i=0; i < buffer; ++i) {
    if (heads.get(i) != -1) {
      if (heads.get(i) != gold_heads.get(i)) {
        penalty+=2;
      }
 else       if (deprels.get(i) != gold_deprels.get(i)) {
        penalty+=1;
      }
    }
  }
  return T[len_l - 1][len_r - 1][0] + penalty;
}
