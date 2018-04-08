# 搭建小型局域网Git Server

首先git是[分布式版本控制系统](https://git-scm.com/book/zh/v2/起步-关于版本控制)，所以我们只需要让网络内的用户可以访问到仓库就完成了。  

[Git 可以使用四种主要的协议来传输资料](https://git-scm.com/book/zh/v2/%E6%9C%8D%E5%8A%A1%E5%99%A8%E4%B8%8A%E7%9A%84-Git-%E5%8D%8F%E8%AE%AE)：本地协议（Local），HTTP 协议，SSH（Secure Shell）协议及 Git 协议。  

我们选择SSH协议，比较适合几个人维护的项目。

## 搭建步骤

1. Server创建git账户，以及authorized_keys用于认证

``` shell
$ sudo adduser git
$ su git
$ cd
$ mkdir .ssh && chmod 700 .ssh
$ touch .ssh/authorized_keys && chmod 600 .ssh/authorized_keys
```

2. 需要访问git server的用户[生成SSH公钥](https://help.github.com/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/)，并加入authorized_keys

``` shell
$ cat /tmp/id_rsa.john.pub >> ~/.ssh/authorized_keys
$ cat /tmp/id_rsa.josie.pub >> ~/.ssh/authorized_keys
$ cat /tmp/id_rsa.jessica.pub >> ~/.ssh/authorized_keys
```

3. 创建一个空仓库

``` shell
$ cd /opt/git
$ mkdir project.git
$ cd project.git
$ git init --bare
Initialized empty Git repository in /opt/git/project.git/
```

4. 这时用户可以克隆代码仓并修改代码了

``` shell
$ git clone git@gitserver:/opt/git/project.git
$ cd project
$ vim README
$ git commit -am 'fix for the README file'
$ git push origin master
```

