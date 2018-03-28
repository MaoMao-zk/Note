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

废弃本地修改
``` bash
git checkout . #本地所有修改的。没有的提交的，都返回到原来的状态
git stash #把所有没有提交的修改暂存到stash里面。可用git stash pop回复。
git reset --hard HASH #返回到某个节点，不保留修改。
git reset --soft HASH #返回到某个节点。保留修改

git clean -df #返回到某个节点
git clean 参数
    -n 显示 将要 删除的 文件 和  目录
    -f 删除 文件
    -df 删除 文件 和 目录
    

也可以使用：
git checkout . && git clean -xdf
```

拉一个分支
``` bash
git clone [url] -b [branch name] --single-branch
```
