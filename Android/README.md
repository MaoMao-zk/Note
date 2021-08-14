
## 学习内容

* JAVA
    * 基本语法
        * [★]~~异常~~
        * [Java 到底是值传递还是引用传递？](https://www.zhihu.com/question/31203609)
    * 特殊语法
        * [★]~~反射~~
        * [★]~~语法糖/注解~~
            * JUnit - Java单元测试框架，使用注解
            * ReactNative使用ReactModule注解构建ModuleRegistry
        * [★]~~动态代理~~
            * Proxy.newProxyInstance
    * 基础API
        * [★]~~线程、异步、并发、线程池~~
            * 线程：继承Thread、继承Runable、继承Callable
            * 线程池：Executor（execute、submit、schedule）
            * 异常：继承Thread.UncaughtExceptionHandler
            * 异步：Future、DelayQueue
            * 并发：Mutex、synchronized、wait() & notify()、AtomicXXX、volatile
            * TODO：Android并发
        * [★]I/O
            * [使用Gradle输入报错问题](https://stackoverflow.com/questions/19344661/run-failed-java-util-nosuchelementexception)
        * [★]~~文本/字符串~~
        * [★]~~序列化~~
            * Java : implements Serializable
            * Android : implements Parcelable
            * Others: Thrift, protobuf ...
        * [★]~~容器~~
        * 匿名函数
            * Lambda 表达式：() -> {}
            * 接口式函数：@FunctionalInterface
        * 网络
        * 图像
        * 音频
        * 加解密
        * [★]数据库
            * JDBC（Java DataBase Connectivity）
                * ![JDBC vs diff DB](http://img.mukewang.com/wiki/5ef2d39b09806ad210391056.jpg)
        * xml
        * 。。。
    * [★]虚拟机
    * [★]GC
        * [Java/Android内存泄漏(Memory Leak)(6种情况)](https://blog.csdn.net/Emmanuel__/article/details/83792868)
        * [Java 内存溢出和Java泄露的几种情况](https://blog.csdn.net/keep_learn/article/details/104656377)
        * TODO: 以上链接有些冗余和错误，需要再整理

* Android
    * [★]开发环境
        * https://developer.android.google.cn/studio/
        * [Could not determine the dependencies of task ':app:compileDebugJavaWithJavac'.](https://zhidao.baidu.com/question/557861208389292692.html)
            * 当compileSdkVersion设置太高时有这种问题，结局方法时设置低一点
        * [Android Studio Build窗口出现中文乱码问题](https://blog.csdn.net/qq_31796651/article/details/108249199)
        * Ctrl+/ 默认在一行的开头加注释，setting里可以设置为按照缩进加注释
            * ![Comment Setting](./.res/comment-setting.png)
            * ![Comment Setting2](./.res/comment-setting-2.png)
    * [★]调试
    * [★]工程
        * Gradle
        * 多渠道打包？ ASM？
    * Android 平台架构
        * ![](https://developer.android.google.cn/guide/platform/images/android-stack_2x.png)
    * AndroidX & Jetpack Compose
    * APIs
        * https://developer.android.google.cn/reference
        * View System(UI)
            * [★]WebKit
        * Resource Managment
        * Notification Managment
        * [★]Activity Managment & Navigation
            * [Android中Activity之间通信](https://www.cnblogs.com/tmlee/p/4978709.html)
            * [Android启动Activity的两种方式与四种启动模式*](https://www.cnblogs.com/chenxibobo/p/6136626.html)
        * [★]Content Provider
        * Device APIs
        * [★]IPC
        * Thread
            * AsyncTask was deprecated!
            * [Android 四种异步操作UI界面的方法](https://blog.csdn.net/qq_34308476/article/details/51392882)
                * Handler - 需要当前线程有Looper在运行
                * AsyncTask - 已经废弃，而且需要在主线程执行
                * View.post（Runnable ) - 需要有view的实例
                * Activity.runOnUiThread（Runnable )  - 需要有activity的实例


## 参考
2021年超详细的-Android超神学习路线总结--纯干货分享（字节，阿里，腾讯大牛联合打造）
* https://zhuanlan.zhihu.com/p/355577587

Android 开发者学习路线（2020 版)
* https://zhuanlan.zhihu.com/p/104878641

Android Best Practices
* https://github.com/futurice/android-best-practices

Advanced Android Development — Practicals
* https://google-developer-training.github.io/android-developer-advanced-course-practicals/

《Android开发艺术探索》 - 源码
* https://github.com/singwhatiwanna/android-art-res