# SpringMVC + Velocity + Jetty 环境搭建

常用 Java 开发环境搭建。框架 SpringMVC，模板 Velocity，开发服务器 Jetty。

## Maven

Maven 是一个 Java 项目的管理工具。主要用于项目依赖包管理，以及项目打包。和 Ubuntu 的 apt，Node.js 的 npm 功能类似。

相比 apt, npm 中的"包"，Maven 中被称为 "artifact"。此外 Maven 中还有 "group"，相当于 "artifact" 所属的组织。一般用倒写域名的形式：如 com.microsoft。

和 npm 的 "package.json" 文件一样，Maven 也有对应的项目配置文件，就是 pom.xml(Project Object Model) 文件。

### Maven 的安装

#### Windows 下安装

1. 下载 Maven。上官方网站[下载页面](http://maven.apache.org/download.cgi)。选择一个压缩包下载。如：	*apache-maven-3.3.9-bin.tar.gz*。
2. 将下载的压缩包解压缩到一个英文目录下，作为 Maven 的安装目录。
3. 设置环境变量。Maven 必须依赖 *JAVA_HOME* 环境变量，所以首先设置 *JAVA_HOME*，设置为 *JDK* 安装目录。如：E:\Program Files\Java\jdk1.8.0_111。

### 创建 Maven 项目

用脚手架工具 `mvn archetype:generate` 可以快速创建一个项目结构。

1. 执行命令 `mvn archetype:generate`。
2. 先输入 *webapp* 过滤掉一部分。
3. 选择 *org.apache.maven.archetypes:maven-archetype-webapp* 行，输入前面的序号。如：29。
4. 接下来选择版本。
5. 输入 groupId, artifactId 等。
6. 项目创建成功。

#### 下载注册表

由于该命令会访问一个远程的注册表，该注册表有数万个脚手架，可以选择一个现成的作为自己的项目目录结构。
远程访问可能会比较慢。命令行有可能会停留在 *"[INFO] Generating project in Interactive mode"* 处无反应。可以将该注册表下载到本地。

具体方法：
1. 从[下载地址](http://repo1.maven.org/maven2/archetype-catalog.xml)下载文件放置到 `~/.m2` 目录中。
2. 执行命令时加上参数：`-DarchetypeCatalog=local`。

#### 在 Eclipse 中的该操作

1. 点击菜单 *File > New > Project* 打开 *New Project* 对话框。
2. 选择 *Maven > Maven Project* 打开 *New Maven Project* 对话框。
3. 不要选中 *Create a simple project(skip archetype)*，否则不会进入脚手架选择界面。点击下一步。
4. 在 *Archetype* 中选择一个，按完成即可。

### Eclipse 中配置 Maven

#### 设置 Installations

1. 打开菜单 *Window > Preference*。
2. 选择 *Maven > installations*, 打开 *Installations* 面板。
3. 点击右边的 *Add* 按钮选择选择目录面板。
4. 点击 *Directory...* 按钮选择刚刚安装的 Maven 目录。如：E:\\maven\\apache-maven-3.3.9。确定后回到 *Installations* 面板。
5. 在选择面板中选择刚刚添加的目录。然后点击 *OK* 保存。

#### 设置 Maven 的配置文件

1. 和上节前两步一样打开 Maven 配置面板。
2. 选择 *Maven > User Settings*。打开面板。
3. 点击 *Global Settings* 标签后的浏览按钮。
4. 选择 Maven 安装目录下 /conf/settings.xml 文件。点击 *OK* 保存。

### 创建经典 SpringMVC + Velocity + Jetty 相关配置

#### 创建、修改配置文件

* /pom.xml
* /.classpath
* /webapp/WEB-INF/web.xml
* /webapp/WEB-INF/applicationContext.xml - SpringMVC - 全局配置文件
* /webapp/WEB-INF/demo-servlet.xml - Servlet - 配置文件
* /webapp/WEB-INF/velocity.properties

#### 创建、修改应用文件

* /webapp/WEB-INF/templates/layout/default.vm
* /webapp/WEB-INF/templates/index.vm
* /webapp/WEB-INF/templates/VM_global_library.vm - 放置一些宏等
* /main/java/me/anrin/controller/HomeController.java

也可以直接 git 克隆。仓库：
* [GitHub](https://github.com/wuanrin/javaweb-demo.git)
* [OSChina](https://git.oschina.net/wuanrin/javaweb-demo.git)

### 编译项目

用 `maven package` 命令，也可以先清除再编译 `mavn clean package`。
编译期间会去仓库下载依赖的包，过程可能会慢。也可以设置一个国内镜像。

#### 添加镜像方法

1. 打开 Maven 的配置文件（在 Maven 安装目录下的 /conf/settings.xml。非项目配置文件）。
2. 在 `<mirrors></mirrors>` 之间加入以下代码：

```xml
<mirror>
  <id>alimaven</id>
  <name>aliyun maven</name>
  <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
  <mirrorOf>central</mirrorOf>
</mirror>
```

#### 在 Eclipse 中的该操作

打开项目的右键菜单：*Maven > Update Project*。

如果出现弹框提示 *"maven could not calculate build plan"* 错误，处理方法如下：
1. 将 ~/.m2/repository/org/apache/maven/plugins/maven-deploy-plugin 目录下的文件全部删除。
2. 强制更新项目：右击项目目录。选择 *Maven > Update Project*。在弹出的窗口中选中 *Force Update of Snapshots/Releases*。*OK* 确认。

### 在 Maven 中配置 Jetty 插件

在 pom.xml `<plugins></plugins>` 中添加：

```xml
<plugin>
  <groupId>org.eclipse.jetty</groupId>
  <artifactId>jetty-maven-plugin</artifactId>
</plugin>
```

### 运行项目

执行命令`mvn jetty:run`。指定端口加上`-Djetty.port=8081`参数。
如果出现 run 命令找不到的错误，可以尝试添加 jetty 的版本信息。如：`<version>9.2.7.v20150116</version>`。
