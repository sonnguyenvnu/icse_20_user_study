package org.knowm.xchange.coinsuper.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.*;

/** æ¨¡æ‹ŸhttpPostæ–¹å¼?å?‘é€?æ•°æ?® */
public class RestHttpClient {
  private static final String HTTPS_PREFIX = "https";

  private static MyX509TrustManager xtm = new MyX509TrustManager();
  private static MyHostnameVerifier hnv = new MyHostnameVerifier();

  /**
   * èŽ·å?–response Stringç±»åž‹å“?åº”æ•°æ?®
   *
   * @param is
   * @param charset
   * @return
   * @throws IOException
   */
  private static String getResponseStringData(InputStream is, String charset) throws IOException {
    ByteArrayOutputStream respDataStream = new ByteArrayOutputStream();
    String result;
    try {
      byte[] b = new byte[8192];
      int len;
      while (true) {
        len = is.read(b);
        if (len <= 0) {
          is.close();
          break;
        }
        respDataStream.write(b, 0, len);
      }
      result = respDataStream.toString(charset);
    } finally {
      respDataStream.close();
    }
    return result;
  }

  /**
   * å?‘é€?httpè¯·æ±‚ï¼Œæ”¯æŒ?httpå’Œhttpsæ–¹å¼?
   *
   * @param serverUrl è¯·æ±‚åœ°å?€
   * @param realData éœ€è¦?å?‘é€?çš„æ•°æ?®
   * @return
   */
  public static String post(
      String serverUrl, String realData, int connectTimeout, int readTimeout, String charset)
      throws NoSuchAlgorithmException, KeyManagementException, IOException {
    if (serverUrl == null || "".equals(serverUrl.trim())) {
      throw new NullPointerException("è¯·æ±‚åœ°å?€ä¸ºç©º!");
    }
    if (realData == null || "".equals(realData.trim())) {
      throw new NullPointerException("è¯·æ±‚æ•°æ?®ä¸ºç©º!");
    }
    URLConnection conn;
    // åˆ†åˆ«è°ƒç”¨httpæˆ–httpsè¯·æ±‚
    if (serverUrl.startsWith(HTTPS_PREFIX)) {
      SSLContext sslContext = SSLContext.getInstance("TLS");
      X509TrustManager[] xtmArray = new X509TrustManager[] {xtm};
      sslContext.init(null, xtmArray, new java.security.SecureRandom());
      HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
      HttpsURLConnection.setDefaultHostnameVerifier(hnv);
      HttpsURLConnection httpsUrlConn = (HttpsURLConnection) (new URL(serverUrl)).openConnection();
      httpsUrlConn.setRequestMethod("POST");
      conn = httpsUrlConn;
    } else {
      URL url = new URL(serverUrl);
      conn = url.openConnection();
    }
    conn.setConnectTimeout(connectTimeout * 1000);
    conn.setReadTimeout(readTimeout * 1000);
    conn.setRequestProperty("Content-Type", "application/json;charset=" + charset);

    conn.setDoOutput(true);
    conn.setDoInput(true);

    OutputStream out = conn.getOutputStream();
    try {
      out.write(realData.getBytes(charset));
      out.flush();
    } finally {
      out.close();
    }

    InputStream is = conn.getInputStream();
    // è¿”å›žæ”¶åˆ°çš„å“?åº”æ•°æ?®
    return getResponseStringData(is, charset);
  }
}

class MyX509TrustManager implements X509TrustManager {

  public void checkClientTrusted(X509Certificate[] chain, String authType) {}

  public void checkServerTrusted(X509Certificate[] chain, String authType) {}

  public X509Certificate[] getAcceptedIssuers() {
    return null;
  }
}

class MyHostnameVerifier implements HostnameVerifier {

  public boolean verify(String hostname, SSLSession session) {
    return true;
  }
}
