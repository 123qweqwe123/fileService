import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

/**
 * @author qianyanan
 * @date2018/4/9 0009下午 2:29
 * <p>
 * 通过HttpClient调用文件上传下载接口DEMO
 */
public class HttpClientUploadDemo {

    @PostMapping("/upload")
    public void save(@RequestParam(value = "file", required = true) MultipartFile file,
                     @RequestParam(value = "project", required = true) String project, String fileType) throws Exception {
        String projectPath = System.getProperty("user.dir");
        String path = projectPath + "/upload/";
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String filePath = path + file.getOriginalFilename();
        File dest = new File(filePath);
        FileUtils.copyInputStreamToFile(file.getInputStream(), dest);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpPost httppost = new HttpPost("http://localhost:7000/common/file/upload");
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(200000).setSocketTimeout(200000).build();
            httppost.setConfig(requestConfig);
            HttpEntity reqEntity = MultipartEntityBuilder.create()
                    .addBinaryBody("file", dest)
                    .addTextBody("project", project)
                    .addTextBody("filetype", fileType)
                    .setCharset(CharsetUtils.get("UTF-8")).build();
            httppost.setEntity(reqEntity);
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    String responseEntityStr = EntityUtils.toString(response.getEntity());
                    System.out.println(responseEntityStr);
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> upload(String fileid) throws URISyntaxException, ClientProtocolException, IOException {
        //创建一个httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个uri对象
        URIBuilder uriBuilder = new URIBuilder("http://localhost:7000/common/file/download");
        uriBuilder.addParameter("fileid", fileid);
        HttpGet get = new HttpGet(uriBuilder.build());
        //执行请求
        CloseableHttpResponse response = httpClient.execute(get);
        //取响应的结果
        HttpEntity entity = response.getEntity();
        String fileName = response.getFirstHeader("fileName").getValue();
        InputStream is = entity.getContent();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] byt = new byte[1024];
        int len = -1;
        while ((len = is.read(byt)) != -1) {
            outStream.write(byt, 0, len);
        }
        outStream.close();
        is.close();
        byte[] newbyt = outStream.toByteArray();
        ByteArrayResource resource = new ByteArrayResource(newbyt);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .contentLength(newbyt.length)
                .body(resource);
    }
}
