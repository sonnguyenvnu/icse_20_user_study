@Override public long length(RendererConfiguration mediaRenderer){
  int cbr_video_bitrate=getDefaultRenderer().getCBRVideoBitrate();
  return (cbr_video_bitrate > 0) ? (long)(((cbr_video_bitrate + 256) * 1024 / (double)8 * 1.04) * getMedia().getDurationInSeconds()) : length();
}
