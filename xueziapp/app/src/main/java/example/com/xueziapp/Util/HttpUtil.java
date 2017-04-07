package example.com.xueziapp.Util;

import android.content.Context;
import android.content.Intent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.sql.Connection;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HttpUtil {
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {

    }

    /**
     * @throws CertificateException
     * @throws IOException
     * @throws KeyStoreException
     * @throws NoSuchAlgorithmException
     * @throws KeyManagementException
     */
    public void initSSL() throws CertificateException, IOException, KeyStoreException,
            NoSuchAlgorithmException, KeyManagementException {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        InputStream in = MyApplication.getContext().getAssets().open("test.cer");
        Certificate ca = cf.generateCertificate(in);

        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        keystore.load(null, null);
        keystore.setCertificateEntry("ca", ca);

        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keystore);

        // Create an SSLContext that uses our TrustManager
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, tmf.getTrustManagers(), null);
        SSLSocketFactory sslSocketFactory = context.getSocketFactory();
        OkHttpClient client = new OkHttpClient.Builder().sslSocketFactory(sslSocketFactory).build();
        Request request = new Request.Builder()
                .url("https://test.gou-jian.com:8443/")
                .build();
        Response response = client.newCall(request).execute();
        final String data = response.body().string();


        //https://test.gou-jian.com:8443/
        //https://certs.cac.washington.edu/CAtest/
//        URL url = new URL("https://test.gou-jian.com:8443/");
//        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
//        urlConnection.setSSLSocketFactory(context.getSocketFactory());
//        InputStream input = urlConnection.getInputStream();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
//        final StringBuffer result = new StringBuffer();
//        String line = "";
//        while ((line = reader.readLine()) != null) {
//            result.append(line);
//        }
//        runOnUiThread(new Runnable() {
//            @Override
//            public void run() {
//                textView.setText(result.toString());
//            }
//        });


    }
}
