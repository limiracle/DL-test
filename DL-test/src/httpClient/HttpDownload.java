package httpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

/**
 * 说明 利用httpclient下载文件 可下载http文件、图片、压缩文件 bug：获取response
 * header中Content-Disposition中filename中文乱码问题
 */
public class HttpDownload {

	public static String download(String url) {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpPost httpost = new HttpPost(url);
//			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//			nvps.add(new BasicNameValuePair("fileName", "/home/lizhi/test8_20150617114426.txt"));
//			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			HttpResponse response = client.execute(httpost);

			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			File file = new File("e:/daling/down1.txt");
			file.getParentFile().mkdirs();
			FileOutputStream fileout = new FileOutputStream(file);
			/**
			 * 根据实际运行效果 设置缓冲区大小
			 */
			byte[] buffer = new byte[1024];
			int ch = 0;
			while ((ch = is.read(buffer)) != -1) {
				fileout.write(buffer, 0, ch);
			}
			is.close();
			fileout.flush();
			fileout.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		// String url =
		// "http://bbs.btwuji.com/job.php?action=download&pid=tpc&tid=320678&aid=216617";
		String url = "http://10.0.20.42:8081/DL/pushFile/downExcel.do?fileName=/home/lizhi/test8_20150617114426.txt";
		// String filepath = "D:\\test\\a.torrent";
		HttpDownload.download(url);
	}
}
