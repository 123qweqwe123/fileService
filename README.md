## 文件上传下载服务接口 ##

本项目提供项目上传下载接口，采用内嵌式H2数据库记录文件信息，查看文件相关信息,目前文件预览只支持office、pdf、图片、文本格式文件.
### 运行部署 ###
需要在服务器安装openoffice，用于office文件转换为pdf文件进行预览。
安装目录为/opt/openoffice4

### 注意事项 ###
H2数据库访问地址
ip:port/h2-console/login.do
项目第一次启动，访问地址，建表，sql文件在Resources/sql目录下。
通过httpclient方式，调用文件上传下载Demo在text目录下。