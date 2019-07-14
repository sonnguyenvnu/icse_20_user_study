@SuppressLint("InflateParams") private View buildView(LayoutInflater inflater){
  View view=inflater.inflate(R.layout.track_selection_dialog,null);
  ViewGroup root=view.findViewById(R.id.root);
  trackViews=new CheckedTextView[trackGroups.length][];
  for (int groupIndex=0; groupIndex < trackGroups.length; groupIndex++) {
    TrackGroup group=trackGroups.get(groupIndex);
    boolean groupIsAdaptive=trackGroupsAdaptive[groupIndex];
    trackViews[groupIndex]=new CheckedTextView[group.length];
    for (int trackIndex=0; trackIndex < group.length; trackIndex++) {
      if (trackIndex == 0) {
        root.addView(inflater.inflate(R.layout.list_divider,root,false));
      }
      int trackViewLayoutId=groupIsAdaptive ? android.R.layout.simple_list_item_multiple_choice : android.R.layout.simple_list_item_single_choice;
      CheckedTextView trackView=(CheckedTextView)inflater.inflate(trackViewLayoutId,root,false);
      trackView.setText(buildTrackName(group.getFormat(trackIndex)));
      if (trackInfo.getTrackFormatSupport(rendererIndex,groupIndex,trackIndex) == RendererCapabilities.FORMAT_HANDLED) {
        trackView.setFocusable(true);
        trackView.setTag(Pair.create(groupIndex,trackIndex));
        trackView.setOnClickListener(this);
      }
 else {
        trackView.setFocusable(false);
        trackView.setEnabled(false);
      }
      trackViews[groupIndex][trackIndex]=trackView;
      root.addView(trackView);
    }
  }
  updateViews();
  return view;
}
