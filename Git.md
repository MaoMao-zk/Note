Git
===========================================================


### 配置用户名密码  
> git config --global user.name "xxx"   
> git config --global user.email "yyy@zzz"

### 配置记住用户名密码
> git config --global credential.helper store
>> 配置之后，通过VSCode提交代码，还是不会记住密码，汗..
>>> 经测试，在VSCode Terminal提交一次之后，在用界面方式提交，就可以不用输用户密码了，囧

### 忽略文件权限
> git config core.filemode false  // 当前版本库  
> git config --global core.fileMode false // 所有版本库

### 配置代理
> http.proxy=109.123.97.11:8080    //http代理  
> http.sslverify=false             //不检查证书  
> https.proxy=109.123.97.11:8080   //https代理  

### 废弃本地修改
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

### 克隆一个分支
``` bash
git clone [url] -b [branch name] --single-branch
```

### 合入patch
``` bash
# 检查是否能够直接合入
git apply --check ../be0d69cd.diff

# 如果有冲突，使用下列命令，有冲突的文件会生成对应的xxx.rej文件
# 合并的日志汇总信息会存在be0d69cd.log
git apply --reject ../be0d69cd.diff  2>&1 | tee ../be0d69cd.log

# 合入多个patch可以用
git am ../patches/*.patch
```

### 查看某一行的改动来自于哪次commit

``` bash
# 其显示格式为： 
# commit ID | 代码提交作者 | 提交时间 | 代码位于文件中的行数 | 实际代码 
git blame file_name
```

### 追加改动到最后一个patch
``` bash
# 1. modify files
# 2. add files
# 3. use "git commit --amend"
git commit --amend
```

### 修改历史中某一个patch的改动
``` bash
# 1. 使用 rebase --interactive 修改某次commit之后的改动
git rebase f0866a803cdb170bdaac538d83b5650aefae7dfd --interactive
# 2. 在弹框中，把想要修改的commit 前面的pick 改为 edit，保存退出
# 3. 这时会从“f0866a803cdb170bdaac538d83b5650aefae7dfd”后面的commits开始rebase，当遇到标记为edit的commit会暂停
# 4. modify files， add files， use "git commit --amend"
# 5. git rebase --continue
git rebase --continue
```

### BFG Repo-Cleaner
Removes large or troublesome blobs like git-filter-branch does, but faster. And written in Scala

https://rtyley.github.io/bfg-repo-cleaner/

### Remove file from repos
https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/removing-sensitive-data-from-a-repository

### git LFS(Large File Storage)
https://about.gitlab.com/blog/2017/01/30/getting-started-with-git-lfs-tutorial/
