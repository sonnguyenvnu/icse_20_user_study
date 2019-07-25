public final void apply(libvlc_media_stats_t stats){
  this.inputBytesRead=stats.i_read_bytes;
  this.inputBitrate=stats.f_input_bitrate;
  this.demuxBytesRead=stats.i_demux_read_bytes;
  this.demuxBitrate=stats.f_demux_bitrate;
  this.demuxCorrupted=stats.i_demux_corrupted;
  this.demuxDiscontinuity=stats.i_demux_discontinuity;
  this.decodedVideo=stats.i_decoded_video;
  this.decodedAudio=stats.i_decoded_audio;
  this.picturesDisplayed=stats.i_displayed_pictures;
  this.picturesLost=stats.i_lost_pictures;
  this.audioBuffersPlayed=stats.i_played_abuffers;
  this.audioBuffersLost=stats.i_lost_abuffers;
  this.sentPackets=stats.i_sent_packets;
  this.sentBytes=stats.i_sent_bytes;
  this.sendBitrate=stats.f_send_bitrate;
}
