<T extends RecyclerView.Adapter & Filterable>void setup(T adapter,ChipsInputLayout chipsInputLayout){
  setAdapter(adapter);
  mFilter=adapter.getFilter();
  mChipsInput=chipsInputLayout;
}
