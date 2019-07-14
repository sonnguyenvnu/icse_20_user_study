void get_oracle_actions2(List<Integer> heads,List<Integer> deprels,List<Action> actions){
  actions.clear();
  int len=heads.size();
  List<Integer> sigma=new ArrayList<Integer>();
  int beta=0;
  List<Integer> output=new ArrayList<Integer>(len);
  for (int i=0; i < len; i++) {
    output.add(-1);
  }
  int step=0;
  while (!(sigma.size() == 1 && beta == len)) {
    int[] beta_reference=new int[]{beta};
    get_oracle_actions_onestep(heads,deprels,sigma,beta_reference,output,actions);
    beta=beta_reference[0];
  }
}
