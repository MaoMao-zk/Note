Git
===========================================================


配置用户名密码  
> git config --global user.name "xxx"   
> git config --global user.email "yyy@zzz"

配置记住用户名密码
> git config --global credential.helper store
>> 配置之后，通过VSCode提交代码，还是不会记住密码，汗..
>>> 经测试，在VSCode Terminal提交一次之后，在用界面方式提交，就可以不用输用户密码了，囧

忽略文件权限
> git config core.filemode false  // 当前版本库  
> git config --global core.fileMode false // 所有版本库

配置代理
> http.proxy=109.123.97.11:8080    //http代理  
> http.sslverify=false             //不检查证书  
> https.proxy=109.123.97.11:8080   //https代理  
